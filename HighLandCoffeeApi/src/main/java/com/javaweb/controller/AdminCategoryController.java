package com.javaweb.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.Http;
import com.javaweb.entity.Category;
import com.javaweb.entity.Customer;
import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.function.ConvertToSlug;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.CategoryService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/admin/category")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class AdminCategoryController {

	private CategoryService categoryService;
	private UserService userService;
	private StaffService staffService;

	public AdminCategoryController(CategoryService categoryService,UserService userService, StaffService staffService) {
		super();
		this.categoryService = categoryService;
		this.userService = userService;
		this.staffService = staffService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createCategoryHandler(@RequestBody Map<String, Object> categoryMap,@RequestHeader("Authorization") String jwt) throws UserException{
		Category category = new Category();
		User user = userService.findUserByJwt(jwt);
		Staff staff = staffService.findStaffByUserId(user.getUser_id());
		String category_name = null;
		if(categoryMap.containsKey("category_name")) {
			category_name = (String) categoryMap.get("category_name");
		}
		Category checkCategoryNameExist = categoryService.findCategoryByName(category_name);
		ApiResponse res = new ApiResponse();
		HttpStatus http = null;
		if(checkCategoryNameExist == null){
		category.setCategory_name(category_name);
		category.setStatus("Active");
		category.setCreated_at(LocalDateTime.now());
		category.setUpdated_at(LocalDateTime.now());
		category.setCreated_by(staff.getStaff_id());
		category.setUpdated_by(staff.getStaff_id());
		category.setSlug(ConvertToSlug.convertToSlug(category_name));
		Category createCategory = categoryService.createCategory(category);
		if (createCategory != null) {
			res.setCode(HttpStatus.CREATED.value());
			res.setMessage("success");
			res.setStatus(true);
			http = HttpStatus.CREATED;
		}else{
			res.setCode(HttpStatus.CONFLICT.value());
			res.setMessage("fail");
			res.setStatus(false);
			http = HttpStatus.CONFLICT;
		}
		}else{
			res.setCode(HttpStatus.CONFLICT.value());
			res.setMessage("category name exist");
			res.setStatus(false);
			http = HttpStatus.CONFLICT;

		}
		return new ResponseEntity<>(res,http);
	}

	@PutMapping("/{id}/update")
	public ResponseEntity<ApiResponse> updateCategoryHandler(@PathVariable Long id,@RequestBody Category category, @RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Staff staff = staffService.findStaffByUserId(user.getUser_id());
		Category update = categoryService.updateCategory(id, staff.getStaff_id(), category);
		ApiResponse res = new ApiResponse();
		HttpStatus http = null;
		if(update != null){
			res.setCode(HttpStatus.OK.value());
			res.setMessage("success");
			res.setStatus(true);
			http =  HttpStatus.OK;
		}else{
			res.setCode(HttpStatus.CONFLICT.value());
			res.setMessage("fail");
			res.setStatus(false);
			http = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<ApiResponse>(res,http);
	}

	
	@PutMapping("/{id}/delete")
	public ResponseEntity<ApiResponse> deleteCategoryHandler(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Staff staff = staffService.findStaffByUserId(user.getUser_id());
		Category delete = categoryService.deleteCategory(id, staff.getStaff_id());
		ApiResponse res = new ApiResponse();
		HttpStatus http = null;
		if(delete != null){
			res.setCode(HttpStatus.OK.value());
			res.setMessage("success");
			res.setStatus(true);
			http =  HttpStatus.OK;
		}else{
			res.setCode(HttpStatus.CONFLICT.value());
			res.setMessage("fail");
			res.setStatus(false);
			http = HttpStatus.CONFLICT;
		}

		return new ResponseEntity<ApiResponse>(res,http);
	}

	@GetMapping("/all")
	public ResponseEntity<ListEntityStatusResponse> getAllCategory(){
		List<Category> listCategory = categoryService.findAll();
		ListEntityStatusResponse res = new ListEntityStatusResponse<>();
		HttpStatus http = null;
		if(listCategory != null){
			res.setData(listCategory);
			res.setMessage("successs");
			res.setStatus(HttpStatus.OK.value());
			http = HttpStatus.OK;
		}else{
			res.setData(null);
			res.setMessage("fail");
			res.setStatus(HttpStatus.CONFLICT.value());
			http = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<>(res,http);
	}
}
