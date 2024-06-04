package com.javaweb.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long customer_id;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private Date birthday;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private String address;
	
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@Column
	private String cccd;
	
	@Column
	private String tax_id;
		
	@Column
	private Long user_id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<CouponDetail> coupon_detail;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id",updatable = false, insertable = false)
	private User user;
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer")
	private Cart cart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Orders> order;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Review> review;

	@JsonIgnore
	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	
	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	
	public String getTax_id() {
		return tax_id;
	}

	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}

	@JsonIgnore
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public List<CouponDetail> getCoupon_detail() {
		return coupon_detail;
	}

	public void setCoupon_detail(List<CouponDetail> coupon_detail) {
		this.coupon_detail = coupon_detail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public Customer(Long customer_id, String firstname, String lastname, Date birthday, String email, String phone,
			String address, LocalDateTime created_at, LocalDateTime updated_at, String cccd, String tax_id, Long user_id,
			List<com.javaweb.entity.CouponDetail> coupon_detail, com.javaweb.entity.User user,
			List<com.javaweb.entity.Orders> order, List<com.javaweb.entity.Review> review) {
		super();
		this.customer_id = customer_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.cccd = cccd;
		this.tax_id = tax_id;
		this.user_id = user_id;
		this.coupon_detail = coupon_detail;
		this.user = user;
		this.order = order;
		this.review = review;
	}

	public Customer() {
		
	}
	
	

}
