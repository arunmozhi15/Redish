package com.myself.practice.globalexception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

	

	@ExceptionHandler(value = Exception.class)
	public  ResponseEntity<?> exceptionHandling(Exception e){
log.debug(e);
		
		var statusCode = 0; 
		
		if( e instanceof IllegalArgumentException || e instanceof MethodArgumentTypeMismatchException || 
			e instanceof MethodArgumentNotValidException) {
			statusCode = 400 ;
        } else if (e instanceof EntityNotFoundException || e instanceof NoResourceFoundException) {
            statusCode = 404 ;
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            statusCode = 405 ;
        }else {
			statusCode = 500 ; 
		}
		
		var model = new ErrorResponse(statusCode,e.getMessage(),null);
		
		log.info(model);	
		
		return ResponseEntity.internalServerError().body(model);	
	}
	
	
	
	
	
	
}
