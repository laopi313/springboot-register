package com.spidersmart.register.exception;

public class RegisterNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterNotFoundException(Long id) {
		super("Could not find register "+id);
	}
}
