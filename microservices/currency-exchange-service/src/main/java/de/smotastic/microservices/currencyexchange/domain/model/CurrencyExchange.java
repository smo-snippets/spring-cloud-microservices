package de.smotastic.microservices.currencyexchange.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CurrencyExchange {

	private String from;
	private String to;

	private BigDecimal conversionMultiple;
	private String environment;


	public CurrencyExchange(String from, String to, BigDecimal conversionMultiple) {
		super();
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}


}
