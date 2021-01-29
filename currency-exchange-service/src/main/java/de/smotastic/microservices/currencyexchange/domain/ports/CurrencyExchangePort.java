package de.smotastic.microservices.currencyexchange.domain.ports;

import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;

public interface CurrencyExchangePort {
	CurrencyExchange findByFromAndTo(String from, String to);
}
