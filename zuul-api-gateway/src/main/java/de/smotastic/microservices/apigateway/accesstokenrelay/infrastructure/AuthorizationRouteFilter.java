package de.smotastic.microservices.apigateway.accesstokenrelay.infrastructure;

/**
 * AuthorizationRouteFilter
 */

import java.security.Principal;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Adds the Authorization Bearer token in the request, to every routed request
 */
@Component
public class AuthorizationRouteFilter extends ZuulFilter {
	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		// secure request
		return getUserPrincipal() instanceof KeycloakAuthenticationToken;
	}

	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	@Override
	public Object run() {
		RequestContext.getCurrentContext().addZuulRequestHeader("Authorization", extractBearer());
		return null;
	}

	protected String extractBearer() {
		return String.format("Bearer %s", extractToken());
	}

	protected String extractToken() {
		Principal principal = getUserPrincipal();
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
		SimpleKeycloakAccount account = (SimpleKeycloakAccount) token.getDetails();
		return account.getKeycloakSecurityContext().getTokenString();
	}

	protected Principal getUserPrincipal() {
		RequestContext context = RequestContext.getCurrentContext();
		Principal principal = context.getRequest().getUserPrincipal();
		return principal != null ? principal : (Principal) context.get(Principal.class.getName());
	}

}

