package de.smotastic.microservices.currencyconversion.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.smotastic.microservices.currencyconversion.domain.CurrencyConversionService;
import de.smotastic.microservices.currencyconversion.domain.port.CurrencyExchangePort;
import de.smotastic.microservices.currencyconversion.domain.usecase.MultiplyCurrencyExchangeUseCase;

@Configuration
public class CurrencyExchangeConfiguration {

	@Autowired
	public CurrencyExchangeProxy proxy;

	@Bean
	public CurrencyExchangePort currencyExchangePort() {
		return new CurrencyExchangeAdapter(proxy);
	}

	@Bean
	public MultiplyCurrencyExchangeUseCase multiplyCurrencyExchangeUseCase(CurrencyExchangePort port) {
		return new CurrencyConversionService(port);
	}
}
