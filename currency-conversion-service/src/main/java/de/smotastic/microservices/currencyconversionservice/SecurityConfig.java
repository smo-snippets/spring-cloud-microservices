package de.smotastic.microservices.currencyconversionservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import de.smotastic.keycloakresourceserverconfig.KeyCloakSecurityConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends KeyCloakSecurityConfig {

}