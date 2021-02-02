package de.smotastic.keycloakresourceservertest;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

class TestPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		TestPropertyValues.of("eureka.client.enabled=false").applyTo(applicationContext);
	}

}
