package com.example.demo.ExceptionHandler;

public class passwordmismatchexception extends RuntimeException {
	private String message ;
	
	public passwordmismatchexception(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
