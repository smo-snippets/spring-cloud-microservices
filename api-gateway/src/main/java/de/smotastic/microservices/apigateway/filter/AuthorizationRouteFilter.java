package de.smotastic.microservices.apigateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

/**
 * AuthorizationRouteFilter
 */
@Component
public class AuthorizationRouteFilter extends KeycloakFilter {

    @Override
    protected boolean isRouteFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext.getCurrentContext().addZuulRequestHeader(AUTHORIZATION, extractBearer());
        return null;
    }
}
