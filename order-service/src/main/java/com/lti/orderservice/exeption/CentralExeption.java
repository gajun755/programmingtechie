package com.lti.orderservice.exeption;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralExeption {

	
	@ExceptionHandler(ProductNotFound.class)
	public ResponseEntity<RestException> productNotFoundException(ProductNotFound productNotFound){
			
		RestException restException=new RestException();
		restException.setErrorCode("500");
		restException.setMessage(productNotFound.getMessage());
		restException.setTimestamp(new Date().toString());
		return new ResponseEntity<>(restException,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
