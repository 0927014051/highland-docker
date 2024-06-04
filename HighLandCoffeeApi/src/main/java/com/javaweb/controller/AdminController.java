package com.javaweb.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.entity.Category;
import com.javaweb.entity.Role;
import com.javaweb.entity.Size;
import com.javaweb.entity.Staff;

import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.request.AddCategorySizeRequest;
import com.javaweb.request.CreateToppingCategoryRequest;
import com.javaweb.response.ApiResponse;
import com.javaweb.service.IImageService;
import com.javaweb.service.RoleService;
import com.javaweb.service.SizeService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/admin")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class AdminController {

	private RoleService roleService;
	private UserService userService;
	private StaffService staffService;
	private IImageService iImageService;
	private SizeService sizeService;

	public AdminController(RoleService roleService, UserService userService, StaffService staffService,
			IImageService iImageService,SizeService sizeService) {
		super();
		this.roleService = roleService;
		this.userService = userService;
		this.staffService = staffService;
		this.iImageService = iImageService;
		this.sizeService = sizeService;
	}

	@PostMapping("/role/add")
	public ResponseEntity<Role> createRoleHanlder(@RequestBody Map<String, Object> roleObjects) {
		Role role = new Role();
		String roleName = null;
		if (roleObjects.containsKey("role_name")) {
			roleName = (String) roleObjects.get("role_name");
		}
		role.setRole_name(roleName);
		Role createdRole = roleService.createRole(role);
		return new ResponseEntity<Role>(createdRole, HttpStatus.ACCEPTED);
	}

	@PostMapping("/topping/add")
	public ResponseEntity<ApiResponse> createToppingHalder(@RequestParam("file") MultipartFile[] files,
			@RequestParam("data") String data, @RequestHeader("Authorization") String jwt) throws UserException {
		try {
			User user = userService.findUserByJwt(jwt);
			Staff staff = staffService.findStaffByUserId(user.getUser_id());
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, CreateToppingCategoryRequest> requestMap = new HashMap<>();
			CreateToppingCategoryRequest rq = objectMapper.readValue(data, CreateToppingCategoryRequest.class);
			requestMap.put(rq.getCategory_name(), rq);
			requestMap.put(rq.getImage(), rq);
			requestMap.put(rq.getTopping_name(), rq);
			requestMap.put(String.valueOf(rq.getTopping_price()), rq);
			rq.setStaff_id(staff.getStaff_id());
			String staff_id = rq.getStaff_id().toString();
			requestMap.put(staff_id, rq);
			for (MultipartFile file : files) {
				try {
					String fileName = iImageService.save(file);
					rq.setImage(fileName);
					requestMap.put(rq.getImage(), rq);
					String imageUrl = iImageService.getImageUrl(fileName);
					// do whatever you want with that
				} catch (Exception e) {
					System.err.println(e);
				}

			}
			ApiResponse response = new ApiResponse("Thêm thành công", true, HttpStatus.CREATED.value());
			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			ApiResponse response = new ApiResponse("Thêm thất bại", false, HttpStatus.BAD_REQUEST.value());

			return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

	}
	@PostMapping("/size/add")
	public ResponseEntity<ApiResponse> createSizeHanlder(@RequestHeader("Authorization") String jwt, @RequestBody AddCategorySizeRequest size) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Staff staff = staffService.findStaffByUserId(user.getUser_id());
		for(Category item: size.getCategory()) {
			//System.err.println("category_name: " + item.getCategory_name());
		}
		Size createSize = sizeService.createSize(size, staff.getStaff_id());
		ApiResponse res = new ApiResponse("add size success", true, HttpStatus.CREATED.value());
		return new ResponseEntity<ApiResponse>(res,HttpStatus.CREATED);
		
	}

}
