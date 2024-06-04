package com.javaweb.request;

public class AddItemRequest {

	private String product_name;

	private int quantity;

	private Integer price;
	
	private String size;
	
	public AddItemRequest() {
		
	}

	//getter and setter


	public int getQuantity() {
		return quantity;
	}
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public AddItemRequest(String product_name, int quantity, Integer price, String size) {
		super();
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
		this.size = size;
	}
	
	

}

