package com.javaweb.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long cart_id;

	@Column
	private int total_price;
	
	@Column
	private int total_quantity;
	
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@Column
	private Long customer_id;
	
	
	@OneToMany(mappedBy = "cart")
	private List<CartDetail> cart_detail;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id",insertable = false, updatable = false)
	private Customer customer;
	
	@JsonIgnore
	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public int getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public List<CartDetail> getCart_detail() {
		return cart_detail;
	}

	public void setCart_detail(List<CartDetail> cart_detail) {
		this.cart_detail = cart_detail;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cart(Long cart_id, int total_price, int total_quantity, LocalDateTime created_at, LocalDateTime updated_at,
			Long customer_id, List<com.javaweb.entity.CartDetail> cart_detail, com.javaweb.entity.Customer customer) {
		super();
		this.cart_id = cart_id;
		this.total_price = total_price;
		this.total_quantity = total_quantity;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.customer_id = customer_id;
		this.cart_detail = cart_detail;
		this.customer = customer;
	}

	public Cart() {
		
	}


	
	
}
