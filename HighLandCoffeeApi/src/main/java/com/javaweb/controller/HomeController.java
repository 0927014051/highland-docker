package com.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.reponsitory.ProductRepo;
import com.javaweb.request.ProductSaleRequest;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.service.ProductService;

@RestController
public class HomeController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> homeController(){
		ApiResponse res = new ApiResponse("Welcome",true,HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	
}
