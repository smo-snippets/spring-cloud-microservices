package de.smotastic.microservices.currencyexchange.application;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import de.smotastic.keycloakresourceservertest.ResourceServerTest;
import de.smotastic.microservices.currencyexchange.infrastructure.CurrencyExchangeRepository;
import de.smotastic.microservices.currencyexchange.infrastructure.entity.CurrencyExchangeEntity;

/** Integration Test for {@link CurrencyExchangeController} */
@ResourceServerTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CurrencyExchangeControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CurrencyExchangeRepository repository;

	@Value("${local.server.port}")
	String shouldPort;

	@Test
	@WithMockUser
	public void shouldFindNewCurrencyExchangeWithGivenEnvironment_whenRequested() throws Exception {
		// given
		final String shouldFrom = "EUR";
		final String shouldTo = "RAD";
		final Integer shouldConversionMultiple = 100;

		final CurrencyExchangeEntity entity = new CurrencyExchangeEntity();
		entity.setId(100L);
		entity.setFrom(shouldFrom);
		entity.setTo(shouldTo);
		entity.setConversionMultiple(new BigDecimal(shouldConversionMultiple));
		repository.save(entity);

		// when
		mockMvc.perform(get("/currency-exchange/from/" + shouldFrom + "/to/" + shouldTo)) //
				.andExpect(status().is2xxSuccessful()) //
				.andExpect(jsonPath("$.environment").value(is(shouldPort))) //
				.andExpect(jsonPath("$.from").value(is(shouldFrom))) //
				.andExpect(jsonPath("$.to").value(is(shouldTo))) //
				.andExpect(jsonPath("$.conversionMultiple").value(is(shouldConversionMultiple), Integer.class))//
		;
	}
}
