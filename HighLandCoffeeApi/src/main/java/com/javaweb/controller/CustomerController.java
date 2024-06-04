package com.javaweb.controller;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.entity.Customer;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.request.ProfileCustomerRequest;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private CustomerService customerService;
	private UserService userService;
	public CustomerController(CustomerService customerService, UserService userService) {
		super();
		this.customerService = customerService;
		this.userService = userService;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/{customerId}/update")
	public ResponseEntity<EntityStatusResponse> updateCustomer(@RequestBody ProfileCustomerRequest customer, @RequestHeader("Authorization") String jwt) throws UserException, ParseException{
		
		User user = userService.findUserByJwt(jwt);
		Customer findCustomer = customerService.findCustomerByUserId(user.getUser_id());
		Customer updatedCustomer = customerService.updateCustomer(customer, findCustomer.getCustomer_id());
		EntityStatusResponse response = new EntityStatusResponse<>();
		if(updatedCustomer != null) {
			response.setData(null);
			response.setMessage("update customer success");
			response.setStatus(HttpStatus.OK.value());
		}
		return new ResponseEntity<EntityStatusResponse>(response,HttpStatus.OK);
	}
	
}
