package com.taborda.productms.application.domain.dto;


import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@JsonPropertyOrder({"id","name","description","price"})
@Getter
@Setter
@ToString
@NoArgsConstructor 
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductDto 
 {
	
	@JsonProperty("id")
	private Long id;
	
	
	@NotNull(message = "Not null") 
	@NotEmpty(message = "Not empty") 
	@Size(min = 5, message= "name is mandatory ")
	@JsonProperty("name")
	private String name;
	
	@NotNull(message = "Not null") 
	@NotEmpty(message = "Not empty") 
	@Size(min = 5, message= "description is mandatory ")
	@JsonProperty("description")
	private String description;
	
	@Valid
	@NotNull(message = "Not null") 
	@DecimalMin(value="0.1" , message= "price is mandatory " )
	@JsonProperty("price")
	private double price;
 
	
 

 

	 
}
