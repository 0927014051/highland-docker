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

@Entity
@Table(name = "coupon")
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long coupon_id;

	@Column
	private String image;
	
	@Column
	private Date start_date;
	
	@Column
	private Date end_date;
	
	@Column
	private String type;
	
	@Column
	private String content;
	
	@Column
	private int quantity;

	@Column
	private int remaining_amount;

	@Column
	private int minimum_value;

	@Column
	private int use_value;

	@Column
	private String status;

	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@Column
	private Long created_by;
	
	@Column
	private Long updated_by;
	
	@JsonIgnore
	@OneToMany(mappedBy = "coupon")
	private List<CouponDetail> coupon_detail;
	
	
	@ManyToOne
	@JoinColumn(name = "created_by",updatable = false, insertable = false)
	private Staff staff_created;
	
	
	@ManyToOne
	@JoinColumn(name = "updated_by",updatable = false, insertable = false)
	private Staff staff_updated;

	public Long getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRemaining_amount() {
		return remaining_amount;
	}

	public void setRemaining_amount(int remaining_amount) {
		this.remaining_amount = remaining_amount;
	}

	public int getMinimum_value() {
		return minimum_value;
	}

	public void setMinimum_value(int minimum_value) {
		this.minimum_value = minimum_value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public List<CouponDetail> getCoupon_detail() {
		return coupon_detail;
	}

	public void setCoupon_detail(List<CouponDetail> coupon_detail) {
		this.coupon_detail = coupon_detail;
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



	public Coupon(Long coupon_id, String image, Date start_date, Date end_date, String type, String content,
			int quantity, int remaining_amount, int minimum_value, int use_value, String status,
			LocalDateTime created_at, LocalDateTime updated_at, Long created_by, Long updated_by,
			List<CouponDetail> coupon_detail, Staff staff_created, Staff staff_updated) {
		this.coupon_id = coupon_id;
		this.image = image;
		this.start_date = start_date;
		this.end_date = end_date;
		this.type = type;
		this.content = content;
		this.quantity = quantity;
		this.remaining_amount = remaining_amount;
		this.minimum_value = minimum_value;
		this.use_value = use_value;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.coupon_detail = coupon_detail;
		this.staff_created = staff_created;
		this.staff_updated = staff_updated;
	}

	public Coupon() {
	}

	public int getUse_value() {
		return use_value;
	}

	public void setUse_value(int use_value) {
		this.use_value = use_value;
	}



	
	
}
