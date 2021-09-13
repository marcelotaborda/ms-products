package com.taborda.productms.application.domain.dto;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taborda.productms.application.domain.Product;
 
@JsonIgnoreProperties(ignoreUnknown = true)
@Validated
public class ProductDto
 {
	
	@JsonProperty("id")
	private Long id;
	
	
	@NotNull  @NotEmpty @Size(min=15, message= "name is mandatory ")
	@JsonProperty("name")
	private String name;
	
	@NotNull @NotEmpty @Size(min=15, message= "description is mandatory ")
	@JsonProperty("description")
	private String description;
	
	@NotNull  @DecimalMin(value="0.1" , message= "price is mandatory " )
	@JsonProperty("price")
	private double price;
	

	public ProductDto() {
		super();
		 
	}


	public ProductDto(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Product converter() {
		return new Product(this.name, this.description, this.price);
	}


	public static List<ProductDto> convertList(List<Product> findAll) {

		List<ProductDto> list = new ArrayList<>();
		
		findAll.forEach(product  -> {
			
			list.add(new ProductDto(product));
			
		});
		
		return list;
	}


	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDto other = (ProductDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	 

}
