package com.javaweb.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "price_update_detail")
public class PriceUpdateDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long price_update_detail_id;
	
	@Column
	private int price_new;
	
	@Column
	private int price_old;
	
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@Column
	private Long created_by;
	
	@Column
	private Long updated_by;
	
	@Column
	private String product_id;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "created_by",insertable = false, updatable = false)
	private Staff staff;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	@JsonIgnore
	public Long getPrice_update_detail_id() {
		return price_update_detail_id;
	}

	public void setPrice_update_detail_id(Long price_update_detail_id) {
		this.price_update_detail_id = price_update_detail_id;
	}

	public int getPrice_new() {
		return price_new;
	}

	public void setPrice_new(int price_new) {
		this.price_new = price_new;
	}

	@JsonIgnore
	public int getPrice_old() {
		return price_old;
	}

	public void setPrice_old(int price_old) {
		this.price_old = price_old;
	}

	@JsonIgnore
	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@JsonIgnore
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	@JsonIgnore
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@JsonIgnore
	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}
	@JsonIgnore
	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public PriceUpdateDetail(Long price_update_detail_id, int price_new, int price_old, LocalDateTime created_at,
			LocalDateTime updated_at, Long created_by, Long updated_by, String product_id, Staff staff,
			Product product) {
		super();
		this.price_update_detail_id = price_update_detail_id;
		this.price_new = price_new;
		this.price_old = price_old;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.product_id = product_id;
		this.staff = staff;
		this.product = product;
	}

	public PriceUpdateDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
