package com.javaweb.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.protobuf.TextFormat.ParseException;
import com.javaweb.entity.Customer;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.CustomerRepo;
import com.javaweb.request.ProfileCustomerRequest;
import com.javaweb.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepo customerRepo;

	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer findCustomerByUserId(Long user_id) {
		return customerRepo.findCustomerByUserid(user_id);
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		return customerRepo.findCustomerByEmail(email);
	}

	@Override
	public Customer findCustomerById(Long customer_id) throws UserException {

		Optional<Customer> customer = customerRepo.findById(customer_id);

		if (customer.isPresent()) {
			return customer.get();
		}
		throw new UserException("Customer not found with id " + customer_id);

	}

	@Override
	public Customer updateCustomer(ProfileCustomerRequest customer, Long customer_id)
			throws UserException, java.text.ParseException {
		Customer findCustomer = findCustomerById(customer_id);
		findCustomer.setAddress(customer.getAddress());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(customer.getBirthday()); // Chuyển đổi từ String sang Date
		findCustomer.setBirthday(date);
		findCustomer.setCccd(customer.getCccd());
		findCustomer.setEmail(customer.getEmail());
		findCustomer.setFirstname(customer.getFirstname());
		findCustomer.setLastname(customer.getLastname());
		findCustomer.setTax_id(customer.getTax_id());
		findCustomer.setUpdated_at(LocalDateTime.now());
		Customer savedCustomer = customerRepo.save(findCustomer);
		return savedCustomer;
	}
}
