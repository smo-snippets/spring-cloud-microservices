package de.smotastic.microservices.currencyexchange.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import de.smotastic.microservices.currencyexchange.infrastructure.entity.CurrencyExchangeEntity;

/**
 * 
 * Test for {@link CurrencyExchangeRepository} <br />
 * Note: It's questionable if a spring generated repository should be unit
 * tested, maybe this only makes sense for custom queries or complicated ones
 */
@DataJpaTest
public class CurrencyExchangeRepositoryTest {

	@Autowired
	CurrencyExchangeRepository sut;

	@Test
	public void shouldFind_whenExisting() {
		// given
		CurrencyExchangeEntity entity = new CurrencyExchangeEntity();
		entity.setId(100L);
		entity.setFrom("FROM");
		entity.setTo("TO");
		entity.setConversionMultiple(new BigDecimal(155));
		sut.save(entity);

		// when
		CurrencyExchangeEntity actual = sut.findByFromAndTo("FROM", "TO");
		// then
		assertThat(actual.getFrom()).isEqualTo(entity.getFrom());
		assertThat(actual.getTo()).isEqualTo(entity.getTo());
		assertThat(actual.getConversionMultiple()).isEqualTo(entity.getConversionMultiple());
	}

	public void shouldNotFind_whenNonExisting() {
		// given
		// when
		CurrencyExchangeEntity actual = sut.findByFromAndTo("ANY", "NONEXISTING");
		// then
		assertNull(actual);
	}
}
