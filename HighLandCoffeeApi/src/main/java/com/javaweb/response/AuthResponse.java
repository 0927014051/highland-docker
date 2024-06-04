package com.javaweb.response;


public class AuthResponse {
	
	private String token;
	
	private boolean status;
	
	public AuthResponse() {

	}

	public AuthResponse(String token, boolean status) {
		super();
		this.token = token;
		this.status = status;
	}

	//getter and setter

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
