package com.shreyas.app.exception;

/**
 * Custom Exception
 * 
 * @author lauer
 *
 */
public class RestApiConsumerException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public RestApiConsumerException() {
		super();
	}

	public RestApiConsumerException(String error) {
		super(error);
	}

	public RestApiConsumerException(String error, Throwable exception) {
		super(error, exception);
	}

}
