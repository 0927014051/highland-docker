package com.javaweb.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Customer;
import com.javaweb.entity.Staff;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.StaffRepo;
import com.javaweb.request.ProfileCustomerRequest;
import com.javaweb.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{
	private StaffRepo staffRepo;

	public StaffServiceImpl(StaffRepo staffRepo) {
		super();
		this.staffRepo = staffRepo;
	}
	
	@Override
	public Staff findStaffByUserId(Long user_id) {
		return staffRepo.findStaffByUserId(user_id);
	}
	
	@Override
	public Staff findStaffById(Long staff_id) throws UserException {
		Optional<Staff> staff = staffRepo.findById(staff_id);

		if (staff.isPresent()) {
			return staff.get();
		}
		throw new UserException("Staff not found with id " + staff_id);
	}
	
	@Override
	public Staff createStaff(Staff staff) {
		return staffRepo.save(staff);
	}
	
	@Override
	public 	Staff updateStaff(ProfileCustomerRequest staff, Long staff_id) throws UserException, ParseException {
		Staff findStaff = findStaffById(staff_id);
		findStaff.setAddress(staff.getAddress());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(staff.getBirthday()); // Chuyển đổi từ String sang Date
		findStaff.setBirthday(date);
		findStaff.setCccd(staff.getCccd());
		findStaff.setEmail(staff.getEmail());
		findStaff.setFirstname(staff.getFirstname());
		findStaff.setLastname(staff.getLastname());
		findStaff.setTax_id(staff.getTax_id());
		findStaff.setUpdated_at(LocalDateTime.now());
		Staff savedStaff = staffRepo.save(findStaff);
		return savedStaff;
	}
}
