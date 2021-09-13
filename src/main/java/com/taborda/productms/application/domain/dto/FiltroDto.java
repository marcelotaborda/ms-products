package com.taborda.productms.application.domain.dto;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taborda.productms.framework.errors.ValidatedParametersException;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FiltroDto {

	@JsonProperty("q")
	@Nullable
	private String q;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonProperty(value = "min_price", defaultValue = "-1", namespace = "min_price", required = false)
	@Nullable
	private String min_price;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonProperty(value = "max_price", defaultValue = "-1", namespace = "max_price", required = false)
	@Nullable
	private String max_price;

	double min_pricedb = -1.0;
	double max_pricedb = -1.0;

	public FiltroDto() {
		super();

	}

	public FiltroDto(String q, String min_price, String max_price) {
		super();
		this.q = q;
		this.min_price = min_price;
		this.max_price = max_price;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public double getMin_price() {

		return min_pricedb;
	}

	public void setMin_price(String min_price) {
		this.min_price = min_price;
	}

	public double getMax_price() {

		return max_pricedb;
	}

	public void setMax_price(String max_price) {
		this.max_price = max_price;
	}

	public boolean isValid() throws ValidatedParametersException {

		try {

			if (null != max_price) {
				max_pricedb = Double.parseDouble(max_price);
			}

			if (null != min_price) {
				min_pricedb = Double.parseDouble(min_price);
			}
		} catch (NumberFormatException e) {
			throw new ValidatedParametersException();
		}

		return true;
	}

}
