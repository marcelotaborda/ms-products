package com.taborda.productms.application.domain.dto;

 

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taborda.productms.framework.errors.ValidatedParametersException;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

 
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FiltroDto { 

	 
	private static final String INVALID_NUMBER_FOR_PRICE = "Invalid Number for price";


	@Nullable
	@JsonProperty("q")
	private String q;

	 
	@Nullable
	@JsonProperty("min_price")
	private String min_price;

	 
	@Nullable
	@JsonProperty("max_price")
	private String max_price;

	private double minPricedb = -1.0;
	private double maxPricedb = -1.0;

 

	public boolean isValid() throws ValidatedParametersException {

		try {

			if (null != max_price) {
				this.maxPricedb = Double.parseDouble(max_price);
				
			}

			if (null != min_price) {
				this.minPricedb = Double.parseDouble(min_price);
			}
		} catch (NumberFormatException e) {
			throw new ValidatedParametersException( INVALID_NUMBER_FOR_PRICE, null, max_price, null, e); 
		}

		return true;
	}

}
