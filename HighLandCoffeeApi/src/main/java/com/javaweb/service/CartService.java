package com.javaweb.service;

import com.javaweb.entity.Cart;
import com.javaweb.entity.Customer;
import com.javaweb.entity.User;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.request.AddItemRequest;


public interface CartService {
	
	public Cart createCart(Cart cart);
	
	public String addCartItem(Long customer_id,AddItemRequest req) throws ProductException;
//	
	public Cart findCartBCustomerId(Long customerId);
//
	public void clearCart(Long customerId);
	
	public Cart findById(Long cart_id) throws UserException;

	public void autoUpdateCart(Long cart_id) throws UserException;
}
