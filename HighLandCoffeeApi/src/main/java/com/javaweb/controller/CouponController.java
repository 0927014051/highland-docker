package com.javaweb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.entity.Coupon;
import com.javaweb.entity.CouponDetail;
import com.javaweb.entity.Customer;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.CouponDetailService;
import com.javaweb.service.CouponService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private CouponDetailService couponDetailService;
    private UserService userService;
    private CustomerService customerService;
    private CouponService couponService;

    public CouponController(CouponDetailService couponDetailService, UserService userService,
            CustomerService customerService, CouponService couponService) {
        this.couponDetailService = couponDetailService;
        this.userService = userService;
        this.customerService = customerService;
        this.couponService = couponService;
    }

    @RequestMapping("/get")
    public ResponseEntity<ApiResponse> getCouponByCustomer(@RequestHeader("Authorization") String jwt,@RequestParam("coupon_id") Long coupon_id) throws UserException{
        ApiResponse res = new ApiResponse();
        HttpStatus http = null;
        User user = userService.findUserByJwt(jwt);
        Customer customer = customerService.findCustomerByUserId(user.getUser_id());
        CouponDetail couponDetail = couponDetailService.customerGetCoupon(customer.getCustomer_id(),coupon_id);
        if(couponDetail != null){
            res.setCode(HttpStatus.OK.value());
            res.setMessage("get success");
            res.setStatus(true);
            http = HttpStatus.OK;
        }else{
            res.setCode(HttpStatus.CONFLICT.value());
            res.setMessage("get fail");
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

    @RequestMapping("/mycoupon")
    public ResponseEntity<ListEntityStatusResponse> allCouponByCustomer(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserByJwt(jwt);
        Customer customer = customerService.findCustomerByUserId(user.getUser_id());
        List<CouponDetail> allCouponCustomer = couponDetailService.findCouponDetailByCustomerId(customer.getCustomer_id());
        ListEntityStatusResponse res = new ListEntityStatusResponse<>();
        res.setData(allCouponCustomer);
        res.setMessage("success");
        res.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
