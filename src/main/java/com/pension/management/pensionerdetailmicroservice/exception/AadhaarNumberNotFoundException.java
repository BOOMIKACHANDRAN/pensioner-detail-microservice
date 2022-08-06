package com.pension.management.pensionerdetailmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AadhaarNumberNotFoundException extends RuntimeException{

	//This method handles the exception for unavailable Aadhaar Number
	public AadhaarNumberNotFoundException(String message) {
		super(message);
	}

}
