package de.smotastic.microservices.currencyexchange.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;
import de.smotastic.microservices.currencyexchange.domain.model.ExchangeCurrencyCommand;
import de.smotastic.microservices.currencyexchange.domain.usecase.ExchangeCurrencyUseCase;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private ExchangeCurrencyUseCase currencyExchangeUseCase;

	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		return currencyExchangeUseCase.execute(ExchangeCurrencyCommand.builder().from(from).to(to)
				.port(environment.getProperty("local.server.port")).build());
	}

}
