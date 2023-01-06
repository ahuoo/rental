package com.prudential.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entity Already Exists!")
public class EntityAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -8444267182934630560L;

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

}
