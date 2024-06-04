package com.javaweb.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long bill_id;
	
	@Column
	private LocalDateTime created_at;

	@Column
	private Long order_id;
	
	@Column
	private Long created_by;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id",insertable = false, updatable = false)
	private Orders order;
	
	
	@ManyToOne
	@JoinColumn(name = "created_by",insertable = false, updatable =  false)
	private Staff staff;

	@JsonIgnore
	public Long getBill_id() {
		return bill_id;
	}

	public void setBill_id(Long bill_id) {
		this.bill_id = bill_id;
	}


	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@JsonIgnore
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	@JsonIgnore
	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public Bill(Long bill_id, LocalDateTime created_at, Long order_id, Long created_by, Orders order, Staff staff) {
		super();
		this.bill_id = bill_id;
		this.created_at = created_at;
		this.order_id = order_id;
		this.created_by = created_by;
		this.order = order;
		this.staff = staff;
	}

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
