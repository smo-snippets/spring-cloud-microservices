package de.smotastic.microservices.currencyconversion.domain.port;

import de.smotastic.microservices.currencyconversion.domain.model.CurrencyConversion;

public interface CurrencyExchangePort {
	CurrencyConversion retrieveExchangeValue(String from, String to);
}
