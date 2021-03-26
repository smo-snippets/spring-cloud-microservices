package de.smotastic.microservices.apigateway.core.infrastructure;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingFilter extends ZuulFilter {

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public Object run() throws ZuulException {
		final HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.debug("Route -> {}: {} ", request.getMethod(), request.getRequestURI());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}
}
