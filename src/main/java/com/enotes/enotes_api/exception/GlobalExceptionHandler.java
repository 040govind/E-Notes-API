package com.enotes.enotes_api.exception;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		log.error("GlobalExceptionHandler :: handleException ::", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e) {
		log.error("GlobalExceptionHandler :: handleNullPointerException ::", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e) {
		log.error("GlobalExceptionHandler :: handleResourceNotFoundException ::", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	// @ExceptionHandler(MethodArgumentNotValidException.class)
	// public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
	// 	//log.error("GlobalExceptionHandler :: handleException ::", e.getMessage());
	// 	   List<ObjectError> allError= e.getBindingResult().getAllErrors();
	// 	   Map<String,Object> error = new LinkedHashMap<>();
	// 	   allError.stream().forEach(er->{
	// 		 String msg = er.getDefaultMessage();
	// 		 String field = ((FieldError)(er)).getField();
	// 		 error.put(field,msg);
	// 	   });
	// 	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	// }

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e) {
		return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
	}

}
