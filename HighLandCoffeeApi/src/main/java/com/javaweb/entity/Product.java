package com.javaweb.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column
	private String product_id;

	@Column(length = 50)
	private String product_name;

	@Column
	private String image;

	@Column(length = 500)
	private String description;

	@Column
	private String status;

	@Column(name = "created_at")
	private LocalDateTime created_at;

	@Column(name = "updated_at")
	private LocalDateTime updated_at;

	@Column
	private Long category_id;

	@Column
	private Long created_by;

	@Column
	private Long updated_by;

	@ManyToOne
	@MapsId("category_id")
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "created_by", insertable = false, updatable = false)
	private Staff staff_created;

	@ManyToOne
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	private Staff staff_updated;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<CartDetail> cart_detail;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> order_detail;

	@OneToMany(mappedBy = "product")
	private List<PriceUpdateDetail> price_update_detail;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Review> review;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	@JsonIgnore
	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public List<CartDetail> getCart_detail() {
		return cart_detail;
	}

	public void setCart_detail(List<CartDetail> cart_detail) {
		this.cart_detail = cart_detail;
	}

	public List<OrderDetail> getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(List<OrderDetail> order_detail) {
		this.order_detail = order_detail;
	}

	public List<PriceUpdateDetail> getPrice_update_detail() {
		return price_update_detail;
	}

	public void setPrice_update_detail(List<PriceUpdateDetail> price_update_detail) {
		this.price_update_detail = price_update_detail;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Review> getReview() {
		return review;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Staff getStaff_created() {
		return staff_created;
	}

	public void setStaff_created(Staff staff_created) {
		this.staff_created = staff_created;
	}

	public Staff getStaff_updated() {
		return staff_updated;
	}

	public void setStaff_updated(Staff staff_updated) {
		this.staff_updated = staff_updated;
	}

	public Product(String product_id, String product_name, String image, String description, String status,
			LocalDateTime created_at, LocalDateTime updated_at, Long category_id, Long created_by, Long updated_by,
			Staff staff_created, Staff staff_updated, List<CartDetail> cart_detail, List<OrderDetail> order_detail,
			List<PriceUpdateDetail> price_update_detail, Category category, List<Review> review) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.image = image;
		this.description = description;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.category_id = category_id;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.staff_created = staff_created;
		this.staff_updated = staff_updated;
		this.cart_detail = cart_detail;
		this.order_detail = order_detail;
		this.price_update_detail = price_update_detail;
		this.category = category;
		this.review = review;
	}

	public Product() {

	}

	
}
