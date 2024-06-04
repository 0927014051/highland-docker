package com.javaweb.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.config.JwtTokenProvider;
import com.javaweb.entity.Cart;
import com.javaweb.entity.Customer;
import com.javaweb.entity.Role;
import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.StaffRepo;
import com.javaweb.reponsitory.UserRepo;
import com.javaweb.request.LoginRequest;
import com.javaweb.request.SignupRequest;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.AuthResponse;
import com.javaweb.service.CartService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.CustomerUserDetails;
import com.javaweb.service.RoleService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")

public class AuthController {

	private UserRepo userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	private CustomerUserDetails customUserDetails;
	private CartService cartService;
	private CustomerService customerService;
	private StaffService staffService;
	private RoleService roleService;
	private UserService userService;
	private String usernameLogout = "";
	private String passwordLogout = "";

	public AuthController(UserRepo userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,
			CustomerUserDetails customUserDetails, CartService cartService, CustomerService customerService,
			StaffService staffService, RoleService roleService, UserService userService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.customUserDetails = customUserDetails;
		this.cartService = cartService;
		this.customerService = customerService;
		this.userService = userService;
		this.staffService = staffService;
		this.roleService = roleService;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> createUserHandler(@Valid @RequestBody SignupRequest user) throws UserException {
		String username = user.getUsername();
		String password = user.getPassword();
		String role_name = user.getRole_name();
		String first_name = user.getFirst_name();
		String last_name = user.getLast_name();
		User isUserExist = userRepository.findByUsername(username);
		Role role = roleService.findRoleByName(role_name);
		// ApiResponse apiResponse = new ApiResponse(username + " :signup
		// success",true,HttpStatus.CREATED.value());
		// Check email exists
		if (isUserExist != null) {
			// System.out.println("--------- exist "+isEmailExist).getEmail());
			ApiResponse apiResponse = new ApiResponse(username + " :signup fail", false, HttpStatus.CONFLICT.value());
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
		}

		User createdUser = new User();
		createdUser.setUsername(username);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setRole_id(role.getRole_id());
		createdUser.setCreated_at(LocalDateTime.now());
		createdUser.setUpdated_at(LocalDateTime.now());
		createdUser.setStatus("Active");
		User savedUser = userRepository.save(createdUser);
		ApiResponse apiResponse = new ApiResponse();
		if (savedUser != null) {
			if (role.getRole_id() == 3) {

				Customer customer = new Customer();
				customer.setFirstname(first_name);
				customer.setLastname(last_name);
				customer.setCreated_at(LocalDateTime.now());
				customer.setUpdated_at(LocalDateTime.now());
				customer.setUser_id(createdUser.getUser_id());
				customer.setPhone(username);
				Customer createdCustomer = customerService.createCustomer(customer);
				if (createdCustomer != null) {
					Cart cart = new Cart();
					cart.setCreated_at(LocalDateTime.now());
					cart.setCustomer_id(customer.getCustomer_id());
					cart.setUpdated_at(LocalDateTime.now());
					Cart createdCart = cartService.createCart(cart);
					apiResponse.setCode(HttpStatus.CREATED.value());
					apiResponse.setMessage( username + " sigup success");
					apiResponse.setStatus(true);					
				}
			}
			if (role.getRole_id() == 2) {
				if (savedUser != null) {
					Staff staff = new Staff();
					staff.setFirstname(first_name);
					staff.setLastname(last_name);
					staff.setCreated_at(LocalDateTime.now());
					staff.setUpdated_at(LocalDateTime.now());
					staff.setUser_id(createdUser.getUser_id());
					staff.setPhone(username);
					Staff createdStaff = staffService.createStaff(staff);
					apiResponse.setCode(HttpStatus.CREATED.value());
					apiResponse.setMessage( username + " sigup success");
					apiResponse.setStatus(true);	
				}
			}

		} else {
			apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage( username + " sigup fail");
			apiResponse.setStatus(false);	
		}
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		usernameLogout = username;
		passwordLogout = password;
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String tokenSevenday = jwtTokenProvider.generateAccessToken(authentication);
		String tokenThirtyDay = jwtTokenProvider.generateRefreshToken(authentication);
		LocalDateTime currentTime = LocalDateTime.now();
		// Cộng thêm 7 ngày
		LocalDateTime expiredAccressToken = currentTime.plus(7, ChronoUnit.DAYS);
		LocalDateTime expiredRefreshToken = currentTime.plus(30, ChronoUnit.DAYS);
		// Chuyển LocalDateTime thành Timestamp
		Timestamp expiredAccressTokenTimestamp = Timestamp.valueOf(expiredAccressToken);
		Timestamp expiredRefreshTokenTimestamp = Timestamp.valueOf(expiredRefreshToken);
		// Create new user
		AuthResponse authResponse = new AuthResponse(tokenSevenday, true);
		authResponse.setStatus(true);
		authResponse.setToken(tokenSevenday);
		User user = userRepository.findByUsername(username);
		user.setAccessToken(tokenThirtyDay);
		user.setRefreshToken(tokenThirtyDay);
		user.setExpiredAccessToken(expiredAccressTokenTimestamp);
		user.setExpiredRefreshToken(expiredRefreshTokenTimestamp);
		userRepository.save(user);
		System.err.println(authentication);
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<AuthResponse> logout(@RequestHeader("Authorization") String jwt) throws UserException {
		String newtoken = jwtTokenProvider.invalidateToken(jwt);
		User user = userService.findUserByJwt(jwt);
		user.setAccessToken(newtoken);
		user.setRefreshToken(newtoken);
		user.setExpiredAccessToken(Timestamp.valueOf(LocalDateTime.now()));
		user.setExpiredRefreshToken(Timestamp.valueOf(LocalDateTime.now()));
		userRepository.save(user);
		AuthResponse res = new AuthResponse();
		res.setStatus(true);
		res.setToken(newtoken);
		return new ResponseEntity<AuthResponse>(res, HttpStatus.OK);
	}
	@PutMapping("/change/{username}")
	public ResponseEntity<ApiResponse> changePasswordUser(@PathVariable String username, @RequestBody User user){
		ApiResponse res = new ApiResponse();
		User find = userService.findUserByUserName(username);
		HttpStatus stt = null;
		if(find != null) {
		String password = passwordEncoder.encode(user.getPassword());
		User savedUser = userService.changePassword(username, password);
		
		
		if(savedUser != null) {
			stt = HttpStatus.OK;

			res.setCode(HttpStatus.OK.value());
			res.setMessage("change password success");
			res.setStatus(true);
		}else {
			stt = HttpStatus.BAD_REQUEST;

			res.setCode(HttpStatus.BAD_REQUEST.value());
			res.setMessage("change password fail");
			res.setStatus(false);
		}
		}else {
			stt = HttpStatus.BAD_REQUEST;
			res.setCode(HttpStatus.BAD_REQUEST.value());
			res.setMessage("not found username");
			res.setStatus(false);			
		}
		
		return new ResponseEntity<ApiResponse>(res,stt);
	}

	@RequestMapping("check/{username}")
	public ResponseEntity<ApiResponse> checkUserExist(@PathVariable String username){
		User user = userService.findUserByUserName(username);
		ApiResponse res = new ApiResponse();
		HttpStatus http = null;
		if(user != null){
			res.setCode(HttpStatus.OK.value());
			res.setMessage("username exist");
			res.setStatus(true);
			http = HttpStatus.OK;
		}else{
			res.setCode(HttpStatus.CONFLICT.value());
			res.setMessage("username not exist");
			res.setStatus(false);
			http = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<>(res,http);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);
		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
