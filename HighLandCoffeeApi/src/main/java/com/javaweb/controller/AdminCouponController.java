package com.javaweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.entity.Coupon;
import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.CouponService;
import com.javaweb.service.IImageService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/admin/coupon")
public class AdminCouponController {

    private CouponService couponService;
    private UserService userService;
    private StaffService staffService;
    private IImageService iImageService;
    
    public AdminCouponController(CouponService couponService, UserService userService, StaffService staffService,
            IImageService iImageService) {
        this.couponService = couponService;
        this.userService = userService;
        this.staffService = staffService;
        this.iImageService = iImageService;
    }

    @RequestMapping("/add")
    public ResponseEntity<ApiResponse> createReponHanlder( @RequestHeader("Authorization") String jwt,@RequestParam("file") MultipartFile[] files, @RequestParam("data") String data) throws UserException{
        User user = userService.findUserByJwt(jwt);
        Staff staff = staffService.findStaffByUserId(user.getUser_id());
        ApiResponse res = new ApiResponse();
        HttpStatus http= null;
       try {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Coupon> rq = new HashMap<>();
        Coupon coupon = objectMapper.readValue(data, Coupon.class);
        rq.put(coupon.getContent(), coupon);
        rq.put(coupon.getImage(), coupon);
        rq.put(coupon.getStatus(), coupon);
        rq.put(coupon.getType(), coupon);
        rq.put(coupon.getEnd_date().toString(), coupon);
        rq.put( String.valueOf(coupon.getMinimum_value()), coupon);
        rq.put(String.valueOf(coupon.getQuantity()), coupon);
        rq.put(String.valueOf(coupon.getRemaining_amount()), coupon);
        rq.put(coupon.getStart_date().toString(), coupon);
        for(MultipartFile file : files){
            String fileName = iImageService.save(file);
            String imageUrl = iImageService.getImageUrl(fileName);
            coupon.setImage(fileName);
            rq.put(coupon.getImage(), coupon);           
            System.err.println(imageUrl + "----- " + fileName); 
        }
        Coupon createCoupon = couponService.createCoupon(coupon, staff.getStaff_id());
        if(createCoupon != null){
            res.setCode(HttpStatus.CREATED.value());
            res.setMessage("success");
            res.setStatus(true);
            http = HttpStatus.CREATED;
        }

       } catch (Exception e) {
        res.setCode(HttpStatus.CONFLICT.value());
        res.setMessage("fail");
        res.setStatus(false);
        http = HttpStatus.CONFLICT;
       }
        return new ResponseEntity<>(res,http);      
    }

    @RequestMapping("/{coupon_id}/status")
    public ResponseEntity<ApiResponse> updateStatusCoupon(@RequestHeader("Authorization") String jwt, @RequestBody Coupon coupon, @PathVariable Long coupon_id) throws UserException, ProductException{
        User user = userService.findUserByJwt(jwt);
        Staff staff = staffService.findStaffByUserId(user.getUser_id());
        Coupon updateCoupon = couponService.updateStatus(coupon_id, coupon);
        ApiResponse res = new ApiResponse();
        HttpStatus http = null;
        if(updateCoupon != null){
            res.setCode(HttpStatus.OK.value());
            res.setMessage("success");
            res.setStatus(true);
            http = HttpStatus.OK;
        }else{
            res.setCode(HttpStatus.CONFLICT.value());
            res.setMessage("fail");
            res.setStatus(false);
            http = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(res,http);
    }
    @RequestMapping("/all")
    public ResponseEntity<ListEntityStatusResponse> getAllCoupon(){
        List<Coupon> listCoupon = couponService.findAll();
        ListEntityStatusResponse res = new ListEntityStatusResponse<>();
        HttpStatus http = null;
        if(listCoupon != null){
            res.setData(listCoupon);
            res.setMessage("successs");
            res.setStatus(HttpStatus.OK.value());
            http = HttpStatus.OK;
        }else{
            res.setData(null);
            res.setMessage("fail");
            res.setStatus(HttpStatus.CONFLICT.value());
            http = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(res,http);
    }

    @GetMapping("/{coupon_id}/find")
    public ResponseEntity<EntityStatusResponse> findCouponByIf(@PathVariable Long coupon_id) throws ProductException{
        Coupon coupon = couponService.findById(coupon_id);
        EntityStatusResponse res = new EntityStatusResponse<>();
        res.setData(coupon);
        res.setMessage("success");
        res.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
}
