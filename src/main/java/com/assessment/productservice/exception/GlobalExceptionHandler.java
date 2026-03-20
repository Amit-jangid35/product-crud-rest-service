package com.assessment.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assessment.productservice.DTO.RestResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<RestResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
		log.warn("Resource not found: {}", ex.getMessage());

		return new ResponseEntity<>(RestResponse.error(404, ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RestResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {

		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).findFirst().orElse("Validation error");

		log.warn("Validation failed: {}", message);

		return new ResponseEntity<>(RestResponse.error(400, message), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<RestResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
		log.warn("Invalid input: {}", ex.getMessage());

		return new ResponseEntity<>(RestResponse.error(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestResponse<Object>> handleGeneral(Exception ex) {
		log.error("Unexpected error occurred", ex);

		return new ResponseEntity<>(RestResponse.error(500, "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}