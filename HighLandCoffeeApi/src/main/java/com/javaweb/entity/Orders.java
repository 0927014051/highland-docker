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
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long order_id;

	@Column
	private int total_price;
	
	@Column
	private int total_quantity;
	
	@Column(name = "update_at")
	private LocalDateTime update_at; 
	
	@Column
	private int status;

	@Column
	private String note;
	
	@Column(name = "create_at")
	private LocalDateTime create_at;
	
	@Column
	private Long customer_id;

	@Column
	private Long staff_id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",updatable = false, insertable = false)
	private Customer customer;
	
	@JsonIgnore
	@OneToOne(mappedBy = "order")
	private Bill bill;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> order_detail;

	@ManyToOne
	@JoinColumn(name = "staff_id",updatable = false, insertable = false)
	private Staff staff;

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@JsonIgnore
	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public List<OrderDetail> getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(List<OrderDetail> order_detail) {
		this.order_detail = order_detail;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}

	public LocalDateTime getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}

	public Long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Long order_id, int total_price, int total_quantity, LocalDateTime update_at, int status, String note,
			LocalDateTime create_at, Long customer_id, Long staff_id, Customer customer, Bill bill,
			List<OrderDetail> order_detail, Staff staff) {
		this.order_id = order_id;
		this.total_price = total_price;
		this.total_quantity = total_quantity;
		this.update_at = update_at;
		this.status = status;
		this.note = note;
		this.create_at = create_at;
		this.customer_id = customer_id;
		this.staff_id = staff_id;
		this.customer = customer;
		this.bill = bill;
		this.order_detail = order_detail;
		this.staff = staff;
	}

	


}
