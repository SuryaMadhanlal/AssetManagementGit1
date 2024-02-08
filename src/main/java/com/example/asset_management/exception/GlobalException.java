package com.example.asset_management.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = AssetException.class)
	public ResponseEntity<String> globalEntity(Exception exception) {

		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<String> globalSQLEntity(SQLIntegrityConstraintViolationException exception) {

		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}

//	@ExceptionHandler
//	public ResponseEntity<String> ConstraintViolationException(
//			javax.validation.ConstraintViolationException exception) {
//		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//
//	}
	
	
	 @ExceptionHandler(ConstraintViolationException.class)
	    @ResponseStatus(HttpStatus.LENGTH_REQUIRED)
	    public ResponseEntity<List<String>> handleValidationException(ConstraintViolationException ex, Model model) {
	        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
	        List<String> messages = new ArrayList<>();
	        for (ConstraintViolation<?> violation : violations) {
	            messages.add(violation.getMessage());
	        }
	       System.err.println("!#$#@#$@#"+messages);
	        return new ResponseEntity<List<String> >(messages, HttpStatus.LENGTH_REQUIRED); // Redirect to a view that displays validation errors
	    }

}
