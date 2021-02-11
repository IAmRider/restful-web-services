package com.practice.restfulwebservices.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super("User not found for id " + message);
	}

}
