package com.example.demo.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidUserObject extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public InvalidUserObject(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
