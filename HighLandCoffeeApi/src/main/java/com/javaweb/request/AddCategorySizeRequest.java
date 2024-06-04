package com.javaweb.request;

import java.util.List;

import com.javaweb.entity.Category;

public class AddCategorySizeRequest {
	
	private List<Category> category;
	private String size_name;
	private float percent;
	public AddCategorySizeRequest(List<Category> category, String size_name, float percent) {
		super();
		this.category = category;
		this.size_name = size_name;
		this.percent = percent;
	}
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	public String getSize_name() {
		return size_name;
	}
	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public AddCategorySizeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
