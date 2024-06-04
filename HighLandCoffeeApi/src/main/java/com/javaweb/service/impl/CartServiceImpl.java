package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.loading.PrivateClassLoader;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Cart;
import com.javaweb.entity.CartDetail;
import com.javaweb.entity.Category_Size;
import com.javaweb.entity.Customer;
import com.javaweb.entity.PriceUpdateDetail;
import com.javaweb.entity.Product;
import com.javaweb.entity.Size;
import com.javaweb.entity.User;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.CartDetailRepo;
import com.javaweb.reponsitory.CartRepo;
import com.javaweb.request.AddItemRequest;
import com.javaweb.service.CartDetailService;
import com.javaweb.service.CartService;
import com.javaweb.service.PriceUpdateService;
import com.javaweb.service.ProductService;
import com.javaweb.service.SizeCategoryService;
import com.javaweb.service.SizeService;

import ch.qos.logback.core.joran.conditional.IfAction;

@Service
public class CartServiceImpl implements CartService {

	private CartRepo cartRepo;
	private CartDetailService cartDetailService;
	private ProductService productService;
	private PriceUpdateService priceUpdateService;
	private CartDetailRepo cartDetailRepo;
	private SizeService sizeService;
	private SizeCategoryService sizeCategoryService;


	public CartServiceImpl(CartRepo cartRepo, CartDetailService cartDetailService, ProductService productService,
			PriceUpdateService priceUpdateService, CartDetailRepo cartDetailRepo,SizeService sizeService,SizeCategoryService sizeCategoryService) {
		this.cartRepo = cartRepo;
		this.cartDetailService = cartDetailService;
		this.productService = productService;
		this.priceUpdateService = priceUpdateService;
		this.cartDetailRepo = cartDetailRepo;
		this.sizeService = sizeService;
		this.sizeCategoryService = sizeCategoryService;	
	}

	@Override
	public Cart createCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public Cart findCartBCustomerId(Long customerId) {
		return cartRepo.findCartBCustomerId(customerId);
	}

	@SuppressWarnings("unused")
	@Override
	public String addCartItem(Long customer_id, AddItemRequest req) throws ProductException {
		Cart cart = cartRepo.findCartBCustomerId(customer_id);
		Product product = productService.findProductByName(req.getProduct_name());
		PriceUpdateDetail priceUpdateDetail = priceUpdateService.findPriceUpdateByProductId(product.getProduct_id());
		// CartDetail isPresent = cartDetailService.isCartDetailExist(cart,
		// product,customer_id );
		CartDetail isCheckCartDetail = cartDetailRepo.findCartDetailByCartIdAndProductIdWithToppingNull(cart.getCart_id(),
					product.getProduct_id(), req.getSize());
		Size size = sizeService.findSizeByName(req.getSize());
		Category_Size categorySize = sizeCategoryService.findCategory_SizeBySizeId(size.getSize_id(),product.getCategory_id());
		
		
			int priceItem = 0;
			int priceSize = 0;
			if (isCheckCartDetail != null) {
				isCheckCartDetail.setQuantity(isCheckCartDetail.getQuantity() + 1);
				priceItem = priceUpdateDetail.getPrice_new();
				priceSize = priceItem + (int) ((priceItem) * categorySize.getPercent()) / 100;	
				isCheckCartDetail.setPrice(priceSize);
				cartDetailRepo.save(isCheckCartDetail);
				int totalPrice = cartDetailRepo.totalPriceByCartId(cart.getCart_id());
				int totalQuantity = cartDetailRepo.totalQuantityByCartId(cart.getCart_id());
				cart.setTotal_price(totalPrice );
				cart.setTotal_quantity(totalQuantity);
				cartRepo.save(cart);
			} else {
				CartDetail cartDetail = new CartDetail();
				cartDetail.setCart_id(cart.getCart_id());
				cartDetail.setProduct_id(product.getProduct_id());				 
				int priceCartDetail = priceUpdateDetail.getPrice_new() ;
				priceSize = priceCartDetail + (int) ((priceCartDetail) * categorySize.getPercent()) / 100;		
				cartDetail.setPrice(priceSize);
				cartDetail.setSize(req.getSize());
				CartDetail createdCartDetail = cartDetailService.createCartDetail(cartDetail);
				int totalPrice = cartDetailRepo.totalPriceByCartId(cart.getCart_id());
				int totalQuantity = cartDetailRepo.totalQuantityByCartId(cart.getCart_id());
				cart.setTotal_price(totalPrice);
				cart.setTotal_quantity(totalQuantity);
				cartRepo.save(cart);
			}		
		return "Item add to cart";

	}

	@Override
	public void clearCart(Long customer_id) {
		Cart cart = cartRepo.findCartBCustomerId(customer_id);
		if (cart != null) {
			cart.getCart_detail().clear();
			cartRepo.save(cart);
		}
	}
	
	@Override
	public Cart findById(Long cart_id) throws UserException  {

		Optional<Cart> cart = cartRepo.findById(cart_id);

		if (cart.isPresent()) {
			return cart.get();
		}
		throw new UserException("Cart not found with id " + cart);

	
	}

	@Override
	public void autoUpdateCart(Long cart_id) throws UserException {
		// TODO Auto-generated method stub
		Cart cart = findById(cart_id);
			
		List<CartDetail> cartDetail = cartDetailService.findCartDetailByCartId(cart_id);
		if(cartDetail.isEmpty()){
			cart.setTotal_price(0);
			cart.setTotal_quantity(0);
			cartRepo.save(cart);	
		}else{
			int totalPrice = cartDetailRepo.totalPriceByCartId(cart.getCart_id());
		int totalQuantity = cartDetailRepo.totalQuantityByCartId(cart.getCart_id());
		cart.setTotal_price(totalPrice);
		cart.setTotal_quantity(totalQuantity);
		cartRepo.save(cart);
		}
	}

}
