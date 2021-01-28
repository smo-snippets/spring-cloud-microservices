package de.smotastic.microservices.apigateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingFilter extends ZuulFilter {

	@Override
	public int filterOrder() {
		return Integer.MIN_VALUE;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public Object run() throws ZuulException {
		log.info("Path: {}", RequestContext.getCurrentContext().getRequest().getPathTranslated());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

}
