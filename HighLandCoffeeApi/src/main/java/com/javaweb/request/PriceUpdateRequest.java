package com.javaweb.request;

public class PriceUpdateRequest {
	private String product_id;
	private int price_new;
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getPrice_new() {
		return price_new;
	}
	public void setPrice_new(int price_new) {
		this.price_new = price_new;
	}
	public PriceUpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
