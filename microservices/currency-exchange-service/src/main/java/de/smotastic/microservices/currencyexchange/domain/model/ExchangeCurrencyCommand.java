package de.smotastic.microservices.currencyexchange.domain.model;

import de.smotastic.UseCaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ExchangeCurrencyCommand implements UseCaseCommand {

	private String from;
	private String to;
	private String port;
}
