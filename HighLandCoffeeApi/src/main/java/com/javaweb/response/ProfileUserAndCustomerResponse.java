package com.javaweb.response;

import com.javaweb.entity.Customer;
import com.javaweb.entity.User;

public class ProfileUserAndCustomerResponse {
	private User user;
	private Customer customer;
	public ProfileUserAndCustomerResponse(User user, Customer customer) {
		this.user = user;
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
