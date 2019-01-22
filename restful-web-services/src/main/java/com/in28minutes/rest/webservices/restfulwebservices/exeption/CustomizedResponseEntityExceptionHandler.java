package com.in28minutes.rest.webservices.restfulwebservices.exeption;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

		@ExceptionHandler(Exception.class)
		public final ResponseEntity<Object> handleAllExceptions
			(Exception ex, WebRequest request) throws Exception 
			{
				ExeptionResponse exeptionResponse = 
						new ExeptionResponse(new Date(), ex.getMessage(), 
								request.getDescription(false));
			return	new ResponseEntity (exeptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		@ExceptionHandler(UserNotFoundException.class)
		public final ResponseEntity<Object> handleUserNotFoundException
			(Exception ex, WebRequest request) throws Exception 
			{
				ExeptionResponse exeptionResponse = 
						new ExeptionResponse(new Date(), ex.getMessage(), 
								request.getDescription(false));
			return	new ResponseEntity (exeptionResponse, HttpStatus.NOT_FOUND);
				
			}
		
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			
			ExeptionResponse exeptionResponse = 
					new ExeptionResponse(new Date(), "Validation Failed", 
							ex.getBindingResult().getAllErrors().toString());
			 
			return	new ResponseEntity (exeptionResponse, HttpStatus.BAD_REQUEST);
		}
	
}
