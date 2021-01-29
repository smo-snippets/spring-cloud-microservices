package de.smotastic.microservices.currencyexchange.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.smotastic.microservices.currencyexchange.domain.CurrencyExchangeService;
import de.smotastic.microservices.currencyexchange.domain.ports.CurrencyExchangePort;
import de.smotastic.microservices.currencyexchange.domain.usecase.ExchangeCurrencyUseCase;

@Configuration
public class CurrencyExchangeConfig {

	@Bean
	public CurrencyExchangePort currencyExchangePort(CurrencyExchangeRepository repository) {
		return new CurrencyExchangeAdapter(repository);
	}
	
	@Bean
	public ExchangeCurrencyUseCase currencyExchangeUseCase(CurrencyExchangePort currencyExchangePort) {
		return new CurrencyExchangeService(currencyExchangePort);
	}
	
}
