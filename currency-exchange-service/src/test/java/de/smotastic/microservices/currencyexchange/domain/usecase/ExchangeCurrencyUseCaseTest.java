package de.smotastic.microservices.currencyexchange.domain.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.smotastic.microservices.currencyexchange.domain.CurrencyExchangeService;
import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;
import de.smotastic.microservices.currencyexchange.domain.model.ExchangeCurrencyCommand;
import de.smotastic.microservices.currencyexchange.domain.port.CurrencyExchangePort;

public class ExchangeCurrencyUseCaseTest {

	CurrencyExchangePort adapter = Mockito.mock(CurrencyExchangePort.class);

	ExchangeCurrencyUseCase sut = new CurrencyExchangeService(adapter);

	@Test
	public void shouldFindCurrency_whenCurrencyExistsInAdapter() {
		// given
		final String shouldEnv = "8000";
		final BigDecimal shouldConversion = new BigDecimal(64);
		final String shouldTo = "USD";
		final String shouldFrom = "INR";
		when(adapter.findByFromAndTo(any(), any()))
				.then(invo -> new CurrencyExchange(invo.getArgument(0), invo.getArgument(1), shouldConversion));
		final ExchangeCurrencyCommand command = ExchangeCurrencyCommand.builder().to(shouldTo).from(shouldFrom)
				.port(shouldEnv).build();

		// when
		CurrencyExchange actual = sut.execute(command);

		// then
		assertThat(actual.getEnvironment()).isEqualTo(shouldEnv);
		assertThat(actual.getConversionMultiple()).isEqualTo(shouldConversion);
		assertThat(actual.getTo()).isEqualTo(shouldTo);
		assertThat(actual.getFrom()).isEqualTo(shouldFrom);
	}

	public void shouldError_whenNoCurrencyExists() {
		// given
		when(adapter.findByFromAndTo(any(), any())).thenReturn(null);
		final ExchangeCurrencyCommand command = ExchangeCurrencyCommand.builder().to("ANY").from("FROM").build();
		// when / then
		assertThrows(RuntimeException.class, () -> sut.execute(command),
				"No Exchange exists, should throw exception");

	}
}
