package com.synchrony.exception;

public class UserRegistrationFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;

	public UserRegistrationFailedException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
}
