package com.shreyas.app.config;

import org.springframework.http.HttpStatus;

public class ApiError {
	 
    private HttpStatus status;
    private String message;
    private String error;
 
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.error = ex.getLocalizedMessage();
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
 
}