package com.despicable.dto;

public class AuthenticationResponse {

	private String jwtToken;

	public AuthenticationResponse(String jwt) {
		
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
