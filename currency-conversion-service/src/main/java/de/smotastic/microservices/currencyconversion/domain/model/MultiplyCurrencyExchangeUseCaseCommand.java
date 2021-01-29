package de.smotastic.microservices.currencyconversion.domain.model;

import java.math.BigDecimal;

import de.smotastic.UseCaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MultiplyCurrencyExchangeUseCaseCommand implements UseCaseCommand{
	private String from;
	private String to;
	private BigDecimal quantity;
}
