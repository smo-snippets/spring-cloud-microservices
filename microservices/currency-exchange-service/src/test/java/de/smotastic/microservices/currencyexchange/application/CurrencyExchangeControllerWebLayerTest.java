package de.smotastic.microservices.currencyexchange.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import de.smotastic.keycloakresourceservertest.ResourceServerTest;
import de.smotastic.microservices.currencyexchange.domain.usecase.ExchangeCurrencyUseCase;

/** Weblayer Test for {@link CurrencyExchangeController} */
@ResourceServerTest
public class CurrencyExchangeControllerWebLayerTest {

	@MockBean
	ExchangeCurrencyUseCase useCase;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void shouldBeUnauthorized401_whenAuthenticationIsNotGiven() throws Exception {
		// given
		// when / then
		mockMvc.perform(get("/currency-exchange/from/USD/to/INR")) //
				.andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}
	@Test
	@WithMockUser
	public void shouldBeSuccessful_whenAuthenticationIsGiven() throws Exception {
		// given
		// when / then
		mockMvc.perform(get("/currency-exchange/from/USD/to/INR")) //
				.andExpect(status().is2xxSuccessful());
	}
}
