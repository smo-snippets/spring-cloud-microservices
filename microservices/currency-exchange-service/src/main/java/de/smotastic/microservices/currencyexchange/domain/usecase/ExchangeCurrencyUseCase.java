package de.smotastic.microservices.currencyexchange.domain.usecase;

import de.smotastic.UseCase;
import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;
import de.smotastic.microservices.currencyexchange.domain.model.ExchangeCurrencyCommand;

public interface ExchangeCurrencyUseCase extends UseCase<CurrencyExchange, ExchangeCurrencyCommand> {

}
