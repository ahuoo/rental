package com.prudential.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Provided date format is not valid use yyyy-MM-dd")
public class DateFormatNotValidException extends Exception {

	private static final long serialVersionUID = -3005528928196340936L;

	public DateFormatNotValidException(String message) {
		super(message);
	}

}
