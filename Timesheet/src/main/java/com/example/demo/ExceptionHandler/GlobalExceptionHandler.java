package com.example.demo.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String resourcenotfoundexception(ResourceNotFoundException ex) {
		String message  = ex.getMessage();
		return message;
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public String duplicateresourceexception(SQLIntegrityConstraintViolationException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(passwordmismatchexception.class)
	public String passwordmismatch(passwordmismatchexception ps) {
		return ps.getMessage();
	}
	
	@ExceptionHandler(DuplicateIdException.class)
	public String duplicatedata(DuplicateIdException dup) {
		return dup.getMessage();
	}
}
