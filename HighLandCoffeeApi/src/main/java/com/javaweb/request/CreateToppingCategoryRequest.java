package com.javaweb.request;

public class CreateToppingCategoryRequest {
	private String topping_name;
	private String image;
	private int topping_price;
	private String category_name;
	private Long staff_id;
	
	
	public Long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
	}
	public String getTopping_name() {
		return topping_name;
	}
	public void setTopping_name(String topping_name) {
		this.topping_name = topping_name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getTopping_price() {
		return topping_price;
	}
	public void setTopping_price(int topping_price) {
		this.topping_price = topping_price;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public CreateToppingCategoryRequest(String topping_name, String image, int topping_price, String category_name,
			Long staff_id) {
		super();
		this.topping_name = topping_name;
		this.image = image;
		this.topping_price = topping_price;
		this.category_name = category_name;
		this.staff_id = staff_id;
	}
	public CreateToppingCategoryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
