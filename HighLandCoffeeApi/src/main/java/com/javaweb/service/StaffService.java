package com.javaweb.service;

import java.text.ParseException;

import com.javaweb.entity.Staff;
import com.javaweb.exception.UserException;
import com.javaweb.request.ProfileCustomerRequest;

public interface StaffService {
	
	Staff findStaffByUserId(Long user_id);
	
	Staff findStaffById(Long staff_id) throws UserException;
	
	Staff createStaff(Staff staff);
	
	Staff updateStaff(ProfileCustomerRequest staff, Long staff_id) throws UserException, ParseException;

}
