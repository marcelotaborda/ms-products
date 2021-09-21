package com.taborda.productms.framework.errors;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidatedParametersException extends  MethodArgumentTypeMismatchException {

	private static final long serialVersionUID = -5988947933835647816L;

	public ValidatedParametersException(Object value, Class<?> requiredType, String name, MethodParameter param,
			Throwable cause) {
		super(value, requiredType, name, param, cause);

	}

}
