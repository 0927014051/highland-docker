package com.javaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category_size")
public class Category_Size {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long category_size_id;
	
	@Column
	private Long size_id;
	
	@Column
	private Long category_id;
	
	@Column
	private float percent;
	
	
	@ManyToOne
	@JoinColumn(name = "size_id",updatable = false, insertable = false)
	private Size size;
	
	
	@ManyToOne
	@JoinColumn(name = "category_id",insertable = false, updatable = false)
	private Category category;

	public Long getCategory_size_id() {
		return category_size_id;
	}

	public void setCategory_size_id(Long category_size_id) {
		this.category_size_id = category_size_id;
	}

	public Long getSize_id() {
		return size_id;
	}

	public void setSize_id(Long size_id) {
		this.size_id = size_id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category_Size(Long category_size_id, Long size_id, Long category_id, float percent, Size size,
			Category category) {
		this.category_size_id = category_size_id;
		this.size_id = size_id;
		this.category_id = category_id;
		this.percent = percent;
		this.size = size;
		this.category = category;
	}

	public Category_Size() {
	}


	
	
	

}
