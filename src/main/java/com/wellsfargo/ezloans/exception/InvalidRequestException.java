package com.wellsfargo.ezloans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends Exception {
	
	public static final long serialVersionUID = 1L;
	
	public InvalidRequestException(String message) {
		super(message);
	}

}
