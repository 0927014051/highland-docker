package com.javaweb.entity;

import java.sql.Timestamp;
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
@Table(name =  "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long user_id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private int points;
	
	@Column
	private String status;
	
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;
	
	@Column
	private Long updated_by;
	
	@Column
	private String accessToken;
	
	@Column
	private String refreshToken;
	
	@Column
	private Timestamp expiredAccessToken;
	
	@Column
	private Timestamp expiredRefreshToken; 
	
	@Column
	private Long role_id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Customer> customer;
	
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Staff> staff;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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

	@JsonIgnore
	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Timestamp getExpiredAccessToken() {
		return expiredAccessToken;
	}

	public void setExpiredAccessToken(Timestamp expiredAccessToken) {
		this.expiredAccessToken = expiredAccessToken;
	}

	public Timestamp getExpiredRefreshToken() {
		return expiredRefreshToken;
	}

	public void setExpiredRefreshToken(Timestamp expiredRefreshToken) {
		this.expiredRefreshToken = expiredRefreshToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public User(Long user_id, String username, String password, int points, String status, LocalDateTime created_at,
			LocalDateTime updated_at, Long updated_by, String accessToken, String refreshToken,
			Timestamp expiredAccessToken, Timestamp expiredRefreshToken, Long role_id, List<Customer> customer,
			Role role, List<Staff> staff) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.points = points;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.updated_by = updated_by;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiredAccessToken = expiredAccessToken;
		this.expiredRefreshToken = expiredRefreshToken;
		this.role_id = role_id;
		this.customer = customer;
		this.role = role;
		this.staff = staff;
	}

	public User() {
		
	}
	
	
	
}
