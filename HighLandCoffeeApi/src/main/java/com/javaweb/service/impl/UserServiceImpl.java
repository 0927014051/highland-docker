package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;

import com.javaweb.config.JwtTokenProvider;
import com.javaweb.entity.Customer;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.CustomerRepo;
import com.javaweb.reponsitory.UserRepo;
import com.javaweb.request.ProfileUserRequest;
import com.javaweb.response.ProfileUserAndCustomerResponse;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	private JwtTokenProvider jwtTokenProvider;
	private CustomerService customerService;
	private CustomerRepo customerRepo;

	public UserServiceImpl(UserRepo userRepo, JwtTokenProvider jwtTokenProvider, CustomerService customerService,
			CustomerRepo customerRepo) {
		this.userRepo = userRepo;
		this.jwtTokenProvider = jwtTokenProvider;
		this.customerService = customerService;
		this.customerRepo = customerRepo;
	}

	@Override
	public User findUserById(Long userId) throws UserException {

		Optional<User> userOptional = userRepo.findById(userId);

		if (userOptional.isPresent()) {
			System.err.println("find user by user id success");
			return userOptional.get();
		}
		throw new UserException("user not found with user id " + userId);
	}

	@Override
	public ProfileUserRequest findUserProfileByJwt(String jwt) throws UserException {

		System.err.println("user service");
		String username = jwtTokenProvider.getUsernameFromJwtToken(jwt);
		System.err.println("username " + username);
		User user = userRepo.findByUsername(username);
		System.err.println("user_id: " + user.getUser_id());
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		ProfileUserRequest profile = new ProfileUserRequest();
		profile.setAccessToken(user.getAccessToken());
		profile.setAddress(customer.getAddress());
		profile.setBirthday(customer.getBirthday());
		profile.setCccd(customer.getCccd());
		profile.setCreated_at(user.getCreated_at());
		profile.setEmail(customer.getEmail());
		profile.setExpiredAccessToken(user.getExpiredAccessToken());
		profile.setExpiredRefreshToken(user.getExpiredRefreshToken());
		profile.setFirstname(customer.getFirstname());
		profile.setLastname(customer.getLastname());
		profile.setPassword(user.getPassword());
		profile.setPhone(customer.getPhone());
		profile.setPoints(user.getPoints());
		profile.setRefreshToken(user.getRefreshToken());
		profile.setRole_id(user.getRole_id());
		profile.setTax_id(customer.getTax_id());
		profile.setUsername(username);
		profile.setUser_id(user.getUser_id());
		if (user == null) {
			throw new UserException("user not exist with username " + username);
		}
		System.err.println("username " + user.getUsername());
		return profile;
	}

	@SuppressWarnings("unused")
	@Override
	public ProfileUserAndCustomerResponse updateUserAndCustomerProfileByJwt(String jwt,
			ProfileUserRequest updateCustomer) throws UserException {
		String username = jwtTokenProvider.getUsernameFromJwtToken(jwt);
		User user = userRepo.findByUsername(username);
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		if (user == null) {
			throw new UserException("User not exist with username " + username);
		}
		customer.setAddress(updateCustomer.getAddress());
		customer.setBirthday(updateCustomer.getBirthday());
		customer.setCccd(updateCustomer.getCccd());
		if (!updateCustomer.getEmail().equals(null)) {
			Customer existingCustomerWithEmail = customerService.findCustomerByEmail(updateCustomer.getEmail());
			if (existingCustomerWithEmail != null
					&& !existingCustomerWithEmail.getEmail().equals(customer.getEmail())) {
				throw new UserException("Email exist");
			}
			if (updateCustomer.getEmail() != null) {
				customer.setEmail(updateCustomer.getEmail());
			}
		}
		customer.setFirstname(updateCustomer.getFirstname());
		customer.setLastname(updateCustomer.getLastname());
		customer.setPhone(updateCustomer.getPhone());
		if (updateCustomer.getPoints() != 0) {
			user.setPoints(updateCustomer.getPoints());
		}
		customer.setTax_id(updateCustomer.getTax_id());
		userRepo.save(user);
		customerRepo.save(customer);
		ProfileUserAndCustomerResponse response = new ProfileUserAndCustomerResponse(user, customer);
		return response;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findUserByJwt(String jwt) throws UserException {
		String username = jwtTokenProvider.getUsernameFromJwtToken(jwt);
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UserException("user not exist with username " + username);
		}
		return user;
	}

	@Override
	public User findUserByUserName(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User updateStatusByUsername(String username, Long staff_id) throws UserException {
		User user = new User();
		user.setStatus("Unactive");
		user.setUpdated_at(LocalDateTime.now());
		user.setUpdated_by(staff_id);
		User savedUser = userRepo.save(user);
		return savedUser;
	}

	@Override
	public User changePassword(String username, String password) {
		User findUser = userRepo.findByUsername(username);
		if (password != null) {
			findUser.setPassword(password);
			findUser.setUpdated_at(LocalDateTime.now());
		}
		return userRepo.save(findUser);
	}

}
