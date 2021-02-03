package de.smotastic.microservices.currencyexchange.domain;

import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;
import de.smotastic.microservices.currencyexchange.domain.model.ExchangeCurrencyCommand;
import de.smotastic.microservices.currencyexchange.domain.port.CurrencyExchangePort;
import de.smotastic.microservices.currencyexchange.domain.usecase.ExchangeCurrencyUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurrencyExchangeService implements ExchangeCurrencyUseCase {

	private final CurrencyExchangePort currencyExchangePort;

	@Override
	public CurrencyExchange execute(ExchangeCurrencyCommand command) {
		CurrencyExchange currencyExchange = currencyExchangePort.findByFromAndTo(command.getFrom(), command.getTo());

		if (currencyExchange == null) {
			throw new RuntimeException("Unable to Find data for " + command.getFrom() + " to " + command.getTo());
		}
		currencyExchange.setEnvironment(command.getPort());

		return currencyExchange;
	}

}
