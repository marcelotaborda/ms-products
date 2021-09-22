package com.taborda.productms.framework.errors;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class MethodNotValidException extends MethodArgumentNotValidException{

 
	private static final long serialVersionUID = 2767916639023401177L;

	public MethodNotValidException(MethodParameter parameter, BindingResult bindingResult) {
		super(parameter, bindingResult);
		
	}

}
