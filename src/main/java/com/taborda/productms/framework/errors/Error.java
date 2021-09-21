package com.taborda.productms.framework.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Error {

	@JsonProperty(value = "status_code", required = true)
	private Integer statusCode;

	@JsonProperty(value = "message", required = true)
	private String message;
 	 
}
