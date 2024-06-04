package com.javaweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.entity.PriceUpdateDetail;
import com.javaweb.entity.Product;
import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.request.CreateProductRequest;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.IImageService;
import com.javaweb.service.PriceUpdateService;
import com.javaweb.service.ProductService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/admin/product")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdminProductController {

	private ProductService productService;
	private UserService userService;
	private StaffService staffService;
	private PriceUpdateService priceUpdateService;
	private IImageService imageService;

	public AdminProductController(ProductService productService, UserService userService, StaffService staffService,
			PriceUpdateService priceUpdateService, IImageService imageService) {
		this.productService = productService;
		this.userService = userService;
		this.staffService = staffService;
		this.priceUpdateService = priceUpdateService;
		this.imageService = imageService;
	}

//	@SuppressWarnings("rawtypes")
//	@PostMapping("/add")
//	public ResponseEntity<EntityStatusResponse> createProductHandler(@RequestParam(name = "file") MultipartFile[] files,@ModelAttribute CreateProductRequest data,@RequestHeader("Authorization") String jwt) throws ProductException, UserException{
//		User user = userService.findUserByJwt(jwt);
//		Staff staff = staffService.findStaffByUserId(user.getUser_id());
//		data.setStaff_id(staff.getStaff_id());
//		System.err.println("category_name: " + data.getCategory_name() + data.getProduct_name());
//		for (MultipartFile file : files) {
//            try {	
//                String fileName = imageService.save(file);
//
//                String imageUrl = imageService.getImageUrl(fileName);
//                data.setImage(fileName);
//                // do whatever you want with that	                    
//            } catch (Exception e) {
//            System.err.println(e);
//            }
//        }	
//		Product createProduct = productService.createProduct(data);	
//
//		EntityStatusResponse response = new EntityStatusResponse(createProduct, HttpStatus.ACCEPTED.value(), "success");
//		return new ResponseEntity<EntityStatusResponse>(response,HttpStatus.CREATED);
//	}
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestHeader("Authorization") String jwt,
			@RequestParam("file") MultipartFile[] files, @RequestParam("data") String data)
			throws JsonMappingException, JsonProcessingException, ProductException, UserException {
		try {
			User user = userService.findUserByJwt(jwt);
			Staff staff = staffService.findStaffByUserId(user.getUser_id());
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, CreateProductRequest> testRequestMap = new HashMap<>();
			CreateProductRequest rq = objectMapper.readValue(data, CreateProductRequest.class);
			// Thêm vào Map với key là productName
			testRequestMap.put(rq.getCategory_name(), rq);
			testRequestMap.put(rq.getDescription(), rq);			
			for (MultipartFile file : files) {
				try {
					
					String fileName = imageService.save(file);
					rq.setImage(fileName);
					testRequestMap.put(rq.getImage(), rq);
					String imageUrl = imageService.getImageUrl(fileName);
					// do whatever you want with that
				
				} catch (Exception e) {
					System.err.println(e);
				}
			}
			
			testRequestMap.put(rq.getProduct_name(), rq);
			testRequestMap.put(rq.getStatus(), rq);
			String price = String.valueOf(rq.getPrice());
			testRequestMap.put(price, rq);
			testRequestMap.put(rq.getDescription(), rq);
			rq.setStaff_id(staff.getStaff_id());
			String staff_id = rq.getStaff_id().toString();
			testRequestMap.put(staff_id, rq);
			Product createProduct = productService.createProduct(rq);
			// System.err.println("data: " + data);
			ApiResponse response = new ApiResponse("Thêm thành công", true, HttpStatus.CREATED.value());
			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			ApiResponse response = new ApiResponse("Thêm thất bại", false, HttpStatus.BAD_REQUEST.value());

			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		}
	}

	@GetMapping("/all")
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ListEntityStatusResponse> findAllProduct() {
		List<Product> listProducts = productService.getAllProducts();
		ListEntityStatusResponse response = new ListEntityStatusResponse<>(listProducts, HttpStatus.OK.value(),
				"success");
		return new ResponseEntity<ListEntityStatusResponse>(response, HttpStatus.OK);
	}

	@PutMapping("{productId}/update")
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ApiResponse> updatePoroductHandler(@PathVariable String productId,
			@RequestHeader("Authorization") String jwt, @RequestParam("file") MultipartFile[] files,
			@RequestParam("data") String data)
			throws JsonMappingException, JsonProcessingException, ProductException, UserException {
		try {
			User user = userService.findUserByJwt(jwt);
			Staff staff = staffService.findStaffByUserId(user.getUser_id());
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, CreateProductRequest> testRequestMap = new HashMap<>();
			CreateProductRequest rq = objectMapper.readValue(data, CreateProductRequest.class);
			// Thêm vào Map với key là productName
			testRequestMap.put(rq.getCategory_name(), rq);
			testRequestMap.put(rq.getDescription(), rq);
			for (MultipartFile file : files) {
				
				if(file.isEmpty()){
					System.err.println("product img" + productId);
					Product product = productService.findProductById(productId);
					rq.setImage(product.getImage());
					testRequestMap.put(rq.getImage(), rq);
					
				}else{
					String fileName = imageService.save(file);
					rq.setImage(fileName);
					testRequestMap.put(rq.getImage(), rq);
					String imageUrl = imageService.getImageUrl(fileName);
				}					
		}
			testRequestMap.put(rq.getProduct_name(), rq);
			testRequestMap.put(rq.getStatus(), rq);
			String price = String.valueOf(rq.getPrice());
			testRequestMap.put(price, rq);
			testRequestMap.put(rq.getDescription(), rq);
			rq.setStaff_id(staff.getStaff_id());
			String staff_id = rq.getStaff_id().toString();
			testRequestMap.put(staff_id, rq);
			Product updatedProduct = productService.updateProduct(productId, rq, staff.getStaff_id());
			ApiResponse response = new ApiResponse("Update product success", true, HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			ApiResponse response = new ApiResponse(e.getMessage(), false, HttpStatus.BAD_REQUEST.value());

			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		}
	}

	@PutMapping("{productId}/updatePrice")
	@SuppressWarnings("rawtypes")
	public ResponseEntity<EntityStatusResponse> updatePriceHandler(@RequestBody PriceUpdateDetail rq,
			@PathVariable String productId, @RequestHeader("Authorization") String jwt)
			throws ProductException, UserException {
		User user = userService.findUserByJwt(jwt);
		Staff staff = staffService.findStaffByUserId(user.getUser_id());
		PriceUpdateDetail updatePrice = priceUpdateService.updatePrice(productId, rq, staff.getStaff_id());
		EntityStatusResponse response = new EntityStatusResponse(updatePrice, HttpStatus.OK.value(), "success");
		return new ResponseEntity<EntityStatusResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable String productId,
			@RequestHeader("Authorization") String jwt) throws ProductException, UserException {
		System.err.println("delete product controller ....");
		User user = userService.findUserByJwt(jwt);
		Staff staff = staffService.findStaffByUserId(user.getUser_id());
		String msg = productService.deleteProduct(productId, staff.getStaff_id());
		System.err.println("delete product controller .... msg " + msg);
		ApiResponse res = new ApiResponse(msg, true, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}

	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] rqs)
			throws ProductException {

		for (CreateProductRequest productRequest : rqs) {
			productService.createProduct(productRequest);
		}

		ApiResponse res = new ApiResponse("product created successfully", true, HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}

	@GetMapping("/find/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable String productId) throws ProductException {
		Product findProduct = productService.findProductById(productId);
		return new ResponseEntity<Product>(findProduct, HttpStatus.OK);
	}

}
