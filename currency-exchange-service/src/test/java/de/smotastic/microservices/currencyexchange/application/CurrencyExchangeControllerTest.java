package de.smotastic.microservices.currencyexchange.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.smotastic.keycloakresourceserverconfig.ResourceServerTest;

@ResourceServerTest
public class CurrencyExchangeControllerTest {

	@Autowired
	CurrencyExchangeController sut;

	@Test
	public void test() {
		// given

		// when
		sut.retrieveExchangeValue("USD", "INR");
		// then
	}
}
