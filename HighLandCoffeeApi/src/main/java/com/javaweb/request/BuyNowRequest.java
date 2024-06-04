package com.javaweb.request;

import java.time.LocalDateTime;

public class BuyNowRequest {
	private int price;
	private String product_id;
	private int quantity;
	private String size;
	private String topping;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getTopping() {
		return topping;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}
	public BuyNowRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BuyNowRequest(int price, String product_id, int quantity, String size, String topping) {
		super();
		this.price = price;
		this.product_id = product_id;
		this.quantity = quantity;
		this.size = size;
		this.topping = topping;
	}
	
	
}
