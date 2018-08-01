package com.shreyas.app.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shreyas.app.exception.RestApiConsumerException;

/**
 * Global Exception Handler
 * 
 * @author lauer
 *
 */
@ControllerAdvice
public class RestApiErrorHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String error = "This should be application specific";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	/**
	 * Example of dealing with Application Specific Exceptions
	 */
	@ExceptionHandler(RestApiConsumerException.class)
	protected ResponseEntity<Object> handleRestApiConsumerException(RestApiConsumerException ex, WebRequest request) {
		String error = "Error calling extenal Api Services";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
