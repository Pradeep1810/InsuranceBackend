package com.insurance.entities;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateEntryException extends RuntimeException{
	
	 private static final long serialVersionUID = 1L;

	    public DuplicateEntryException(String message) {
	        super(message);
	    }

	    public static void handle(DataIntegrityViolationException ex, String entityName) {
	        throw new DuplicateEntryException(String.format("%s already exists", entityName));
	    }
	    
	    public static void handle(DataIntegrityViolationException ex, String entityName , String entityName2) {
	        throw new DuplicateEntryException(String.format("%s and %s already exists", entityName , entityName2));
	    }

}
