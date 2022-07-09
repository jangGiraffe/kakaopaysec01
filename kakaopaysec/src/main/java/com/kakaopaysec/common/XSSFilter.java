package com.kakaopaysec.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class XSSFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
	  boolean pass = false;
	  String[] excludes = {"swagger","webjars","/"};
	  String url = ((HttpServletRequest)request).getRequestURI();
	  
	  for(String tmp : excludes) {
		  if(url.contains(tmp)) {
			  pass=true;
			  break;
		  }
	  }

	  if(pass) chain.doFilter((HttpServletRequest)request, response);
	  else chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
   }

} 