package com.javaweb.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.entity.Cart;
import com.javaweb.entity.CartDetail;
import com.javaweb.entity.Customer;
import com.javaweb.entity.User;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.request.AddItemRequest;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.service.CartDetailService;
import com.javaweb.service.CartService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private CartService cartService;
	private UserService userService;
	private CustomerService customerService;
	private CartDetailService cartDetailService;
	public CartController(CartService cartService, UserService userService,CustomerService customerService,CartDetailService cartDetailService) {
		this.cartService = cartService;
		this.userService = userService;
		this.customerService = customerService;
		this.cartDetailService = cartDetailService;
	}
	
	@GetMapping("/")
	public ResponseEntity<EntityStatusResponse<Cart>> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.findUserByJwt(jwt);
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		Cart cart = cartService.findCartBCustomerId(customer.getCustomer_id());
		System.err.println("cart - " + cart.getCustomer().getEmail());
		cartService.autoUpdateCart(cart.getCart_id());
		EntityStatusResponse<Cart> responseData = new EntityStatusResponse<>(cart,HttpStatus.OK.value(),"The request was successfully completed");
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user = userService.findUserByJwt(jwt);
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		cartService.addCartItem(customer.getCustomer_id(), req);
		ApiResponse response = new ApiResponse("Item add to cart",true,HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/delete/item")
	public ResponseEntity<ApiResponse> deleeteCartDetailItem(@RequestHeader("Authorization") String jwt,@RequestBody CartDetail item) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		Cart cart = cartService.findCartBCustomerId(customer.getCustomer_id());
		cartDetailService.deleteItemCartDetail(item.getProduct_id(), item.getSize(),cart.getCart_id());
		System.err.println("product_id" + item.getProduct_id() + "   " + item.getSize());
		cartService.autoUpdateCart(cart.getCart_id());
		ApiResponse res = new ApiResponse("delete item success", true, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@PutMapping("/increment/quantity")
	public ResponseEntity<ApiResponse> updateQuantityCartDetail(@RequestHeader("Authorization") String jwt,@RequestBody CartDetail item) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		Cart cart = cartService.findCartBCustomerId(customer.getCustomer_id());
		System.err.println(cart.getCart_id() + " " + item.getProduct_id() + " " + item.getSize());
		cartDetailService.incrementQuantity(cart.getCart_id(),item.getProduct_id(),item.getSize());
		ApiResponse res = new ApiResponse("success", true, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@PutMapping("/reduce/quantity")
	public ResponseEntity<ApiResponse> reduceQuantityCartDetail(@RequestHeader("Authorization") String jwt,@RequestBody CartDetail item) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		Cart cart = cartService.findCartBCustomerId(customer.getCustomer_id());
		System.err.println(cart.getCart_id() + " " + item.getProduct_id() + " " + item.getSize());
		cartDetailService.reduceQuantity(cart.getCart_id(),item.getProduct_id(),item.getSize());
		ApiResponse res = new ApiResponse("success", true, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@PutMapping("/update/quantity")
	public ResponseEntity<ApiResponse> updateQuantity(@RequestHeader("Authorization") String jwt,@RequestBody CartDetail item) throws UserException{
		User user = userService.findUserByJwt(jwt);
		Customer customer = customerService.findCustomerByUserId(user.getUser_id());
		Cart cart = cartService.findCartBCustomerId(customer.getCustomer_id());
		cartDetailService.updateQuantity(cart.getCart_id(),item.getProduct_id(),item.getSize(),item.getQuantity());
		ApiResponse res = new ApiResponse("success", true, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}

}
