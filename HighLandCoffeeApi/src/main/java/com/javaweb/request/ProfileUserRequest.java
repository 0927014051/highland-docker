package com.javaweb.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;

public class ProfileUserRequest {
	
private Long user_id;
	
	private String username;
	
	private String password;
	
	private int points;
	
	private LocalDateTime created_at;
		
	private String accessToken;
	
	private String refreshToken;
	
	private Timestamp expiredAccessToken;
	
	private Timestamp expiredRefreshToken; 
	
	private Long role_id;
	
	private Long customer_id;
	
	private String firstname;
	
	private String lastname;
	
	private Date birthday;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String cccd;
	
	private String tax_id;

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

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

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

	public ProfileUserRequest(Long user_id, String username, String password, int points, LocalDateTime created_at,
			String accessToken, String refreshToken, Timestamp expiredAccessToken, Timestamp expiredRefreshToken,
			Long role_id, Long customer_id, String firstname, String lastname, Date birthday, String email,
			String phone, String address, String cccd, String tax_id) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.points = points;
		this.created_at = created_at;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiredAccessToken = expiredAccessToken;
		this.expiredRefreshToken = expiredRefreshToken;
		this.role_id = role_id;
		this.customer_id = customer_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.cccd = cccd;
		this.tax_id = tax_id;
	}

	public ProfileUserRequest() {
		// TODO Auto-generated constructor stub
	}

	

}
