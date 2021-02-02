package de.smotastic.keycloakresourceservertest;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
/**
 * https://rieckpil.de/spring-boot-integration-tests-with-wiremock-and-junit-5/
 */
class KeycloakWireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
		WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
		wireMockServer.start();

		configurableApplicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

		configurableApplicationContext.addApplicationListener(applicationEvent -> {
			if (applicationEvent instanceof ContextClosedEvent) {
				wireMockServer.stop();
			}
		});

		final String propName = "spring.security.oauth2.resourceserver.jwt.issuer-uri";
		final String keycloakRealm = "testrealm";
		final String mockServerUrl = "http://localhost:" + wireMockServer.port();
		final String keycloakBaseUrl = mockServerUrl + "/auth/realms/" + keycloakRealm;
		TestPropertyValues.of(propName + "=" + keycloakBaseUrl).applyTo(configurableApplicationContext);
		// https://stackoverflow.com/a/60396907/9479695
		// @formatter:off
		 String openidConfig = "{\n" + //
                 "  \"issuer\": \"" + keycloakBaseUrl + "\",\n" + //
                 "  \"authorization_endpoint\": \"" + keycloakBaseUrl + "/protocol/openid-connect/auth\",\n" + //
                 "  \"token_endpoint\": \"" + keycloakBaseUrl + "/protocol/openid-connect/token\",\n" + //
                 "  \"token_introspection_endpoint\": \"" + keycloakBaseUrl+ "/protocol/openid-connect/token/introspect\",\n" + //
                 "  \"userinfo_endpoint\": \"" + keycloakBaseUrl + "/protocol/openid-connect/userinfo\",\n" + //
                 "  \"end_session_endpoint\": \"" + keycloakBaseUrl + "/protocol/openid-connect/logout\",\n" + //
                 "  \"jwks_uri\": \"" + keycloakBaseUrl + "/protocol/openid-connect/certs\",\n" + //
                 "  \"check_session_iframe\": \"" + keycloakBaseUrl + "/protocol/openid-connect/login-status-iframe.html\",\n" + //
                 "  \"registration_endpoint\": \"" + keycloakBaseUrl + "/clients-registrations/openid-connect\",\n" + //
					"  \"introspection_endpoint\": \"" + keycloakBaseUrl + "/protocol/openid-connect/token/introspect\"\n" + //
					"}";
				  // @formatter:on

		final String issuerUri = "/auth/realms/" + keycloakRealm + "/.well-known/openid-configuration";
		wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(issuerUri)).willReturn(
				WireMock.aResponse().withHeader("Content-Type", "application/json").withBody(openidConfig)));
	}
}
