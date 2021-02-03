package de.smotastic.microservices.currencyexchange.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.smotastic.microservices.currencyexchange.domain.model.CurrencyExchange;
import de.smotastic.microservices.currencyexchange.infrastructure.entity.CurrencyExchangeEntity;

public class CurrencyExchangeAdapterTest {

	CurrencyExchangeRepository repository = Mockito.mock(CurrencyExchangeRepository.class);

	CurrencyExchangeAdapter sut = new CurrencyExchangeAdapter(repository);

	@Test
	public void shouldFind_whenCurrencyExists() {
		// given
		String shouldFrom = "USD";
		String shouldTo = "INR";
		BigDecimal shouldConversionMultiple = new BigDecimal(65);
		CurrencyExchangeEntity shouldEntity = new CurrencyExchangeEntity();
		shouldEntity.setTo(shouldTo);
		shouldEntity.setFrom(shouldFrom);
		shouldEntity.setConversionMultiple(shouldConversionMultiple);
		Mockito.when(repository.findByFromAndTo(Mockito.any(), Mockito.any())).thenReturn(shouldEntity);
		// when
		CurrencyExchange actual = sut.findByFromAndTo(shouldFrom, shouldTo);
		// then
		assertThat(actual.getFrom()).isEqualTo(shouldFrom);
		assertThat(actual.getTo()).isEqualTo(shouldTo);
		assertThat(actual.getConversionMultiple()).isEqualTo(shouldConversionMultiple);
	}
}
