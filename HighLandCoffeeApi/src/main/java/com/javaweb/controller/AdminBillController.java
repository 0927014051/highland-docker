package com.javaweb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.entity.Bill;
import com.javaweb.entity.Orders;
import com.javaweb.entity.Product;
import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.BillService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/admin/bill")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class AdminBillController {
	
	private BillService billService;
	private UserService userService;
	private StaffService staffService;

	public AdminBillController(BillService billService, UserService userService, StaffService staffService) {
		super();
		this.billService = billService;
		this.userService = userService;
		this.staffService = staffService;
	}

	@PostMapping("create")
	public ResponseEntity<ApiResponse> createBillHandler(@RequestBody Orders orders, @RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Staff staff =  staffService.findStaffByUserId(user.getUser_id());
		Bill saveBill = billService.createBill(orders, staff.getStaff_id());
		
		ApiResponse apiResponse = new ApiResponse();
		if(saveBill != null) {
			apiResponse.setMessage("create bill success");
			apiResponse.setStatus(true);
			apiResponse.setCode(HttpStatus.CREATED.value());
		}
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ListEntityStatusResponse> getAllBill(){
		List<Bill> findAll = billService.findAll();		
		ListEntityStatusResponse response = new ListEntityStatusResponse<>(findAll, HttpStatus.OK.value(), "success");
		return new ResponseEntity<ListEntityStatusResponse>(response,HttpStatus.OK);
	}

}
