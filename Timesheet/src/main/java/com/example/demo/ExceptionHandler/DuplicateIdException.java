package com.example.demo.ExceptionHandler;

public class DuplicateIdException extends RuntimeException {
	private String message;
	
	public DuplicateIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
