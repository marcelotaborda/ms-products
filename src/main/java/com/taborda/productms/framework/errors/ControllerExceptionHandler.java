package com.taborda.productms.framework.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	private static final String INVALID_VALUE_FOR_PARAMETER = "Invalid value for parameter ";
	private static final String INVALID_JSON = "Invalid json!";

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Error resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		return new Error(404, ex.getMessage());
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Error validatedParametersException(MethodArgumentTypeMismatchException ex) {

		return new Error(400, INVALID_VALUE_FOR_PARAMETER);
	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Error methodNotValidException(MethodArgumentNotValidException ex) {

		return new Error(400, INVALID_VALUE_FOR_PARAMETER);
	}


	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Error validatedParametersException(HttpMessageNotReadableException ex, WebRequest request) {

		return new Error(400, INVALID_JSON);
	}

}
