package com.javaweb.service;

import java.text.ParseException;

import com.javaweb.entity.Customer;
import com.javaweb.exception.UserException;
import com.javaweb.request.ProfileCustomerRequest;

public interface CustomerService {

	public Customer createCustomer(Customer customer);
	
	public Customer findCustomerByUserId(Long user_id);
	
	public Customer findCustomerByEmail(String email);
	
	public Customer findCustomerById(Long customer_id) throws UserException;
	
	public Customer updateCustomer(ProfileCustomerRequest customer, Long customer_id) throws UserException, ParseException;
}
