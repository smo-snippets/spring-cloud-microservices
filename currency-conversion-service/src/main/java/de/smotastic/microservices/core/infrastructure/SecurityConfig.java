package de.smotastic.microservices.core.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import de.smotastic.feigntokenrelayinterceptor.FeignClientTokenRelayInterceptor;
import de.smotastic.keycloakresourceserverconfig.KeyCloakSecurityConfig;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = { FeignClientTokenRelayInterceptor.class })
public class SecurityConfig extends KeyCloakSecurityConfig {

}