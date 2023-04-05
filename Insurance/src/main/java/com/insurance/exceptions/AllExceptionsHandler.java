package com.insurance.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.insurance.entities.DuplicateEntryException;



@RestControllerAdvice
public class AllExceptionsHandler {

	// this class handles and passes the message to the client about the error 
		// this class handles all the exceptions
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionHandler (UserNotFoundException em){
		
		String message = em.getMessage();
		
		return  ResponseEntity.ok(message);

	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException em){
		
		String message = em.getMessage();
		
		return  ResponseEntity.ok(message);

	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> apiExceptionHandler(ApiException em)
	
	{
		
        String message = em.getMessage();
		
		return  ResponseEntity.ok(message);	
	}
	
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<?> duplicateEntryException(DuplicateEntryException em)
	
	{
		
        String message = em.getMessage();
		
		return  ResponseEntity.ok(message);	
	}
	
}
