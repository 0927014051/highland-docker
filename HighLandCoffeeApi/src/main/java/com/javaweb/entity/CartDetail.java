package com.javaweb.entity;

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
@Table(name =  "cart_detail")
public class CartDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long cart_detail_id;
	
	@Column
	private int quantity;
	
	@Column
	private int price;
	
	@Column
	private String size;
	
	@Column
	private String topping;
	
	@Column
	private String product_id;
	
	@Column
	private Long cart_id;
	
	
	@ManyToOne
	@JoinColumn(name =  "product_id",updatable =  false, insertable =  false)
	private Product product;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cart_id", insertable = false, updatable = false)
	private Cart cart;

	@JsonIgnore
	public Long getCart_detail_id() {
		return cart_detail_id;
	}

	public void setCart_detail_id(Long cart_detail_id) {
		this.cart_detail_id = cart_detail_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

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
	 
	@JsonIgnore
	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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

	public CartDetail(Long cart_detail_id, int quantity, int price, String size, String topping, String product_id,
			Long cart_id, Product product, Cart cart) {
		super();
		this.cart_detail_id = cart_detail_id;
		this.quantity = quantity;
		this.price = price;
		this.size = size;
		this.topping = topping;
		this.product_id = product_id;
		this.cart_id = cart_id;
		this.product = product;
		this.cart = cart;
	}	
	
	public CartDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
