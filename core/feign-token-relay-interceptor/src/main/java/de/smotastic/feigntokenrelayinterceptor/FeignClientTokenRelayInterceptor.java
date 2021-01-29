package de.smotastic.feigntokenrelayinterceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignClientTokenRelayInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String TOKEN_TYPE = "Bearer";

	@Override
	public void apply(RequestTemplate requestTemplate) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getCredentials() instanceof Jwt) {
			Jwt cred = (Jwt) authentication.getCredentials();
			requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, cred.getTokenValue()));
		}
	}
}