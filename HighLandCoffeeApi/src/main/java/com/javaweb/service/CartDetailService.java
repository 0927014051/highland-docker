package com.javaweb.service;

import com.javaweb.entity.Cart;
import com.javaweb.entity.CartDetail;
import com.javaweb.entity.Product;
import com.javaweb.exception.CartDetailException;
import com.javaweb.exception.UserException;
import java.util.List;
public interface CartDetailService {
public CartDetail createCartDetail(CartDetail cartItem);
	
	public CartDetail updateCartDetail(Long userId, Long id,CartDetail cartItem) throws CartDetailException, UserException;
	
	public CartDetail isCartDetailExist(Cart cart,Product product, Long userId);
	
	public void removeCartDetail(Long userId,Long cartItemId) throws CartDetailException, UserException;
	
	public CartDetail findCartDetailById(Long cartItemId) throws CartDetailException;
	
	public CartDetail findCartDetailByCartIdAndProductIdWithTopping(Long cart_id,String product_id, String size, String topping);
	
	public CartDetail findCartDetailByCartIdAndProductIdWithToppingNull(Long cart_id,String product_id, String size);
	
	public List<CartDetail> findCartDetailByCartId(Long cart_id);
	
	public void deleteCartDetail(Long cart_id);
	
	public void deleteItemCartDetail(String product_id, String size,Long cart_id) throws UserException;
	
	public void incrementQuantity(Long cart_id, String product_id, String size );

	public void reduceQuantity(Long cart_id, String product_id, String size );
	
	public void updateQuantity(Long cart_id, String product_id, String size,int quantity ) throws UserException;




}
