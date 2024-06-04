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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long category_id;
	
	@Column
	private String category_name;

	@Column
	private String status;
	
	@Column
	private String slug;
	
	@Column
	private Long created_by;
	
	@Column
	private Long updated_by;
	
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "created_by", insertable = false, updatable = false)
	private Staff staff_created;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "updated_by",insertable = false, updatable = false)
	private Staff staff_updated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> product;

	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Category_Size> category_size;

	
	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@JsonIgnore
	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
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

	public List<Category_Size> getCategory_size() {
		return category_size;
	}

	public void setCategory_size(List<Category_Size> category_size) {
		this.category_size = category_size;
	}

	public Category() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Category(Long category_id, String category_name, String status, String slug, Long created_by,
			Long updated_by, LocalDateTime created_at, LocalDateTime updated_at, Staff staff_created,
			Staff staff_updated, List<Product> product,
			List<Category_Size> category_size) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.status = status;
		this.slug = slug;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.staff_created = staff_created;
		this.staff_updated = staff_updated;
		this.product = product;
		this.category_size = category_size;
	}

 
	
	
	

}
