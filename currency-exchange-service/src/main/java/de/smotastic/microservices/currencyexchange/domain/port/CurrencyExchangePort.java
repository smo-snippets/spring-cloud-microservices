package de.smotastic.microservices.currencyexchange.domain.port;

import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;

public interface CurrencyExchangePort {
	CurrencyExchange findByFromAndTo(String from, String to);
}
