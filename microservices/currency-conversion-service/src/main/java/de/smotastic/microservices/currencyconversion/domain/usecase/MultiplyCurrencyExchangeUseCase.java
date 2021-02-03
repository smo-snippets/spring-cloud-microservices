package de.smotastic.microservices.currencyconversion.domain.usecase;

import de.smotastic.UseCase;
import de.smotastic.microservices.currencyconversion.domain.model.CurrencyConversion;
import de.smotastic.microservices.currencyconversion.domain.model.MultiplyCurrencyExchangeUseCaseCommand;

public interface MultiplyCurrencyExchangeUseCase
		extends UseCase<CurrencyConversion, MultiplyCurrencyExchangeUseCaseCommand> {

}
