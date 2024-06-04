package com.javaweb.controller;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.request.ProfileCustomerRequest;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/staff")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class AdminStaffController {

	private UserService userService;
	private StaffService staffService;
	public AdminStaffController(UserService userService, StaffService staffService) {
		super();
		this.userService = userService;
		this.staffService = staffService;
	}
	
	@PutMapping("/{staffId}/update")
	public ResponseEntity<EntityStatusResponse> updateProfileStaff(@RequestBody ProfileCustomerRequest staff,@RequestHeader("Authorization") String jwt) throws UserException, ParseException{
		User user = userService.findUserByJwt(jwt);
		Staff findStaff = staffService.findStaffByUserId(user.getUser_id());
		Staff updateStaff = staffService.updateStaff(staff, findStaff.getStaff_id());
		EntityStatusResponse res = new EntityStatusResponse<>();
		if(updateStaff != null) {
			res.setData(updateStaff);
			res.setMessage("Update staff success");
			res.setStatus(HttpStatus.OK.value());
		}else {
			res.setData(null);
			res.setMessage("Update staff fail");
			res.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		return new ResponseEntity<EntityStatusResponse>(res,HttpStatus.OK);
	}
}
