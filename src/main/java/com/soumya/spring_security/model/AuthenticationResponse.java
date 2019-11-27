package com.soumya.spring_security.model;

public class AuthenticationResponse {
	
	private String jwt;
	
	public AuthenticationResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return this.jwt;
	}
	
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
