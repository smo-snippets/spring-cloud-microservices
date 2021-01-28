package de.smotastic.microservices.apigateway.filter;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * KeycloakFilter
 */
public abstract class KeycloakFilter extends ZuulFilter {

    protected static final String AUTHORIZATION = "Authorization";

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return isSecureRequest();
    }

    @Override
    public String filterType() {
        if (isRouteFilter()) {
            return "route";
        }
        if (isPreFilter()) {
            return "pre";
        }
        if (isPostFilter()) {
            return "post";
        }
        throw new IllegalStateException("One of isRouteFilter/isPreFilter/isPostFilter must be overriden");
    }

    protected boolean isRouteFilter() {
        return false;
    }

    protected boolean isPreFilter() {
        return false;
    }

    protected boolean isPostFilter() {
        return false;
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

    protected HttpServletResponse getResponse() {
        return RequestContext.getCurrentContext().getResponse();
    }

    protected boolean isSecureRequest() {
        return getUserPrincipal() instanceof KeycloakAuthenticationToken;
    }

    protected Principal getUserPrincipal() {
        RequestContext context = RequestContext.getCurrentContext();
        Principal principal = context.getRequest().getUserPrincipal();
        return principal != null ? principal : (Principal) context.get(Principal.class.getName());
    }
}
