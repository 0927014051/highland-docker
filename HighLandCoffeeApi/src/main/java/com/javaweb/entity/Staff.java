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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long staff_id;
	
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
	private long salary;
	
	@Column
	private Long user_id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff")
	private List<Bill> bill;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_created")
	private List<Size> size_created;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_updated")
	private List<Size> size_updated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_updated")
	private List<Category> category_updated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_created")
	private List<Category> category_created;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_updated")
	private List<Product> product_updated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_created")
	private List<Product> product_created;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff")
	private List<PriceUpdateDetail> price_update_detail;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_created")
	private List<Coupon> coupon_created;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff_updated")
	private List<Coupon> coupon_updated;

	@JsonIgnore
	@OneToMany(mappedBy = "staff")
	private List<Orders> orders;
	
	public Long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
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

	@JsonIgnore
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

	
	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	public List<PriceUpdateDetail> getPrice_update_detail() {
		return price_update_detail;
	}

	public void setPrice_update_detail(List<PriceUpdateDetail> price_update_detail) {
		this.price_update_detail = price_update_detail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Size> getSize_created() {
		return size_created;
	}

	public void setSize_created(List<Size> size_created) {
		this.size_created = size_created;
	}

	public List<Size> getSize_updated() {
		return size_updated;
	}

	public void setSize_updated(List<Size> size_updated) {
		this.size_updated = size_updated;
	}

	public List<Category> getCategory_updated() {
		return category_updated;
	}

	public void setCategory_updated(List<Category> category_updated) {
		this.category_updated = category_updated;
	}

	public List<Category> getCategory_created() {
		return category_created;
	}

	public void setCategory_created(List<Category> category_created) {
		this.category_created = category_created;
	}

	public List<Product> getProduct_updated() {
		return product_updated;
	}

	public void setProduct_updated(List<Product> product_updated) {
		this.product_updated = product_updated;
	}

	public List<Product> getProduct_created() {
		return product_created;
	}

	public void setProduct_created(List<Product> product_created) {
		this.product_created = product_created;
	}

	public List<Coupon> getCoupon_created() {
		return coupon_created;
	}

	public void setCoupon_created(List<Coupon> coupon_created) {
		this.coupon_created = coupon_created;
	}

	public List<Coupon> getCoupon_updated() {
		return coupon_updated;
	}

	public void setCoupon_updated(List<Coupon> coupon_updated) {
		this.coupon_updated = coupon_updated;
	}

	
	public Staff(Long staff_id, String firstname, String lastname, Date birthday, String email, String phone,
			String address, LocalDateTime created_at, LocalDateTime updated_at, String cccd, String tax_id, long salary,
			Long user_id, List<Bill> bill, List<Size> size_created, List<Size> size_updated,
			List<Category> category_updated, List<Category> category_created, List<Product> product_updated,
			List<Product> product_created, List<PriceUpdateDetail> price_update_detail, User user,
			List<Coupon> coupon_created, List<Coupon> coupon_updated, List<Orders> orders) {
		this.staff_id = staff_id;
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
		this.salary = salary;
		this.user_id = user_id;
		this.bill = bill;
		this.size_created = size_created;
		this.size_updated = size_updated;
		this.category_updated = category_updated;
		this.category_created = category_created;
		this.product_updated = product_updated;
		this.product_created = product_created;
		this.price_update_detail = price_update_detail;
		this.user = user;
		this.coupon_created = coupon_created;
		this.coupon_updated = coupon_updated;
		this.orders = orders;
	}

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	
	

}
