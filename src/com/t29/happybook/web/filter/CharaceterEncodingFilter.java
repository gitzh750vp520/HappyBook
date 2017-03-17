package com.t29.happybook.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharaceterEncodingFilter implements Filter {


	private String encoding;


	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {		
		req.setCharacterEncoding(encoding);
		filterChain.doFilter(req, resp);		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
		this.encoding  = filterConfig.getInitParameter("encoding");
		if(this.encoding == null) {
			this.encoding = "UTF-8";
		}
		System.out.println("\nCharaceterEncodingFilter 实例化...初始参数“encoding”的值为" + this.encoding + "\n");
	}


	@Override
	public void destroy() {
		
	}
}
