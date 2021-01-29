package de.smotastic.microservices.currencyexchange.infrastructure;

import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;
import de.smotastic.microservices.currencyexchange.domain.ports.CurrencyExchangePort;
import de.smotastic.microservices.currencyexchange.infrastructure.entity.CurrencyExchangeEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurrencyExchangeAdapter implements CurrencyExchangePort {

	private final CurrencyExchangeRepository repository;

	@Override
	public CurrencyExchange findByFromAndTo(String from, String to) {
		final CurrencyExchangeEntity find = repository.findByFromAndTo(from, to);
		return new CurrencyExchange(find.getFrom(), find.getTo(), find.getConversionMultiple());
	}

}
