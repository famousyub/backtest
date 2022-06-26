package com.omnia.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException {

	private static final long serialVersionUID = 1219173814301130447L;
	private String resourceName;
	private String fieldName;

	public DuplicateResourceException(String resourceName, String fieldName) {
		super(String.format("A %s with the same %s exists already", resourceName, fieldName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

}
