package com.pragma.crudpeople.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex ) {
		
		ErrorObject eObject = new ErrorObject();
		eObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		eObject.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NOT_FOUND);		
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleNoDataFoundException(NoDataFoundException ex ) {
		
		ErrorObject eObject = new ErrorObject();
		eObject.setStatusCode(HttpStatus.NO_CONTENT.value());
		eObject.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NO_CONTENT);		
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleBadRequestException(BadRequestException ex ) {
		
		ErrorObject eObject = new ErrorObject();
		eObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		eObject.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorObject>(eObject, HttpStatus.BAD_REQUEST);		
		
	}

}
