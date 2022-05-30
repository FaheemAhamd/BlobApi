package com.web.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resousrceName;
	String fieldName;
	long fielValue;
	public ResourceNotFoundException(String resousrceName, String fieldName, long fielValue) {
		super(String.format("%s not found with %s: %s",resousrceName ,fieldName, fielValue));
		this.resousrceName = resousrceName;
		this.fieldName = fieldName;
		this.fielValue = fielValue;
	}
	
	
}
