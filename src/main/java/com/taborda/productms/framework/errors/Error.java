package com.taborda.productms.framework.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

 
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-19T16:41:58.109Z")
public class Error {

	@JsonProperty(value = "status_code", required = true)
	private Integer status_code;

	@JsonProperty(value = "message", required = true)
	private String message;

	public Error() {
		super();
	}

	public Error(Integer status_code, String message) {
		super();
		this.status_code = status_code; 
		this.message = message;
	}

	public Integer getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
