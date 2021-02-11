package com.practice.restfulwebservices.aop;

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

import com.practice.restfulwebservices.exception.ExceptionResponse;
import com.practice.restfulwebservices.exception.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
		ExceptionResponse exresponse = new ExceptionResponse(ex.getMessage(), request.getDescription(true), new Date());
		return new ResponseEntity<>(exresponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {
		ExceptionResponse exresponse = new ExceptionResponse(ex.getMessage(), request.getDescription(true), new Date());
		return new ResponseEntity<>(exresponse, HttpStatus.NOT_FOUND);
	}

	
	// override method for handle the validation 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse er = new ExceptionResponse(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}

}
