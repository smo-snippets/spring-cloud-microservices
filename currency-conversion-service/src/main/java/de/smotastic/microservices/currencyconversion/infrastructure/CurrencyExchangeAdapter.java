package de.smotastic.microservices.currencyconversion.infrastructure;

import de.smotastic.microservices.currencyconversion.domain.model.CurrencyConversion;
import de.smotastic.microservices.currencyconversion.domain.ports.CurrencyExchangePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurrencyExchangeAdapter implements CurrencyExchangePort {

	private final CurrencyExchangeProxy proxy;
	
	@Override
	public CurrencyConversion retrieveExchangeValue(String from, String to) {
		return proxy.retrieveExchangeValue(from, to);
	}

}
