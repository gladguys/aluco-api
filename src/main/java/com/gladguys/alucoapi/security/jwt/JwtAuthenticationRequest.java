package com.gladguys.alucoapi.security.jwt;

import lombok.Data;

import java.io.Serializable;

public @Data class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1l;

	private String email;
	private String password;


	public JwtAuthenticationRequest() {
		super();
	}

	public JwtAuthenticationRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}