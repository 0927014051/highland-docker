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
@Table(name = "coupon_detail")
public class CouponDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long coupon_detail_id;
	
	@Column
	private Long customer_id;
	
	@Column
	private Long coupon_id;
	
	@Column
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", updatable = false, insertable = false)
	private Customer customer;
	
	@ManyToOne 
	@JoinColumn(name = "coupon_id",insertable =  false, updatable = false)
	private Coupon coupon;

	public Long getCoupon_detail_id() {
		return coupon_detail_id;
	}

	public void setCoupon_detail_id(Long coupon_detail_id) {
		this.coupon_detail_id = coupon_detail_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public CouponDetail(Long coupon_detail_id, Long customer_id, Long coupon_id, String status,
			com.javaweb.entity.Customer customer, com.javaweb.entity.Coupon coupon) {
		super();
		this.coupon_detail_id = coupon_detail_id;
		this.customer_id = customer_id;
		this.coupon_id = coupon_id;
		this.status = status;
		this.customer = customer;
		this.coupon = coupon;
	}

	public CouponDetail() {
	}
	
	
	
}
