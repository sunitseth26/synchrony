package com.synchrony.execption.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synchrony.exception.UserRegistrationFailedException;
import com.synchrony.model.ErrorDetails;

@ControllerAdvice
public class LoginExceptionHandler {

	@ExceptionHandler(UserRegistrationFailedException.class)
	@ResponseBody
	public ResponseEntity<ErrorDetails> userRegistrationFailureHandler(Throwable t) {
		ErrorDetails errorDetails = ErrorDetails.builder().statusCode(500)
				.message(t.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ErrorDetails> exceptionHandler(Throwable t) {
		ErrorDetails errorDetails = ErrorDetails.builder().statusCode(500)
				.message(t.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
