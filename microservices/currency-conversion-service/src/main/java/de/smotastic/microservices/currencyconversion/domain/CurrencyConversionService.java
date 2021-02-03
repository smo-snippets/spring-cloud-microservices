package de.smotastic.microservices.currencyconversion.domain;

import de.smotastic.microservices.currencyconversion.domain.model.CurrencyConversion;
import de.smotastic.microservices.currencyconversion.domain.model.MultiplyCurrencyExchangeUseCaseCommand;
import de.smotastic.microservices.currencyconversion.domain.port.CurrencyExchangePort;
import de.smotastic.microservices.currencyconversion.domain.usecase.MultiplyCurrencyExchangeUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurrencyConversionService implements MultiplyCurrencyExchangeUseCase {

	private final CurrencyExchangePort currencyExchangePort;

	@Override
	public CurrencyConversion execute(MultiplyCurrencyExchangeUseCaseCommand command) {
		CurrencyConversion currencyConversion = currencyExchangePort.retrieveExchangeValue(command.getFrom(),
				command.getTo());

		return new CurrencyConversion(currencyConversion.getId(), command.getFrom(), command.getTo(),
				command.getQuantity(), currencyConversion.getConversionMultiple(),
				command.getQuantity().multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment());
	}

}
