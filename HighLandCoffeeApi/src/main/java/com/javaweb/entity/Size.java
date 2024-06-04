package com.javaweb.entity;

import java.time.LocalDateTime;
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
@Table(name = "size")
public class Size {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long size_id;
	
	@Column
	private String size_name;
	
	@Column
	private Long created_by;
	
	@Column
	private Long update_by;
	
	@Column
	private LocalDateTime created_at;
	
	@Column 
	private LocalDateTime updated_at;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "created_by", updatable =  false, insertable = false)
	private Staff staff_created;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	private Staff staff_updated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "size")
	private List<Category_Size> category_size;

	public Long getSize_id() {
		return size_id;
	}

	public void setSize_id(Long size_id) {
		this.size_id = size_id;
	}

	public String getSize_name() {
		return size_name;
	}

	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(Long update_by) {
		this.update_by = update_by;
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

	public Size(Long size_id, String size_name, Long created_by, Long update_by, LocalDateTime created_at,
			LocalDateTime updated_at, Staff staff_created, Staff staff_updated, List<Category_Size> category_size) {
		super();
		this.size_id = size_id;
		this.size_name = size_name;
		this.created_by = created_by;
		this.update_by = update_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.staff_created = staff_created;
		this.staff_updated = staff_updated;
		this.category_size = category_size;
	}

	public Size() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
