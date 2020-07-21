package com.in28minutes.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	// since we want this filter to be excuted for all request we set shouldFilter to true;
	@Override
	public boolean shouldFilter() {
		return true;
	}

	// here is where the real logic of the filtering happens. we want to log the details of the request
	@Override
	public Object run() {
		HttpServletRequest request = 
				RequestContext.getCurrentContext().getRequest();// this would give me the current request context
		logger.info("request -> {} request uri -> {}", 
				request, request.getRequestURI());
		return null;
	}
// when shoudl the filtering happen before the request is sent or after or only filter error request.
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
}
