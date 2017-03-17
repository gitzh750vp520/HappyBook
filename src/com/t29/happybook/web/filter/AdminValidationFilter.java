package com.t29.happybook.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AdminValidationFilter implements Filter {
	
	private String[] noNeedValidationURIArr; 

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String uri = request.getRequestURI(); // uri : /hb/mgr/xxx
		boolean hasLogin = request.getSession().getAttribute("currAdmin") != null;		
		System.out.println("admin filter : uri[" + uri + "], 是否登录[" + hasLogin + "]");
		if(hasLogin || checkNoNeedValidation(uri)) {
			chain.doFilter(req, resp);
		} else {
			request.setAttribute("tip", "请先登录再进行其它操作！");
			request.getRequestDispatcher("/mgr/admin/loginForm").forward(request, resp);
		}
	}

	// 检查当前这次请求的uri是否是属于不需要验证的uri
	private boolean checkNoNeedValidation(String uri) {
		for(String noNeedValidationURI : noNeedValidationURIArr) {
			if(noNeedValidationURI.trim().equals(uri)) {
				return  true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String noNeedValidationURIs = filterConfig.getInitParameter("noNeedValidationURIs");	
		noNeedValidationURIArr = noNeedValidationURIs.split(",");
		System.out.println("\nAdminValidationFilter 实例化... 不需要验证的URI，遍历如下：");
		for(String noNeedValidationURI : noNeedValidationURIArr) {
			System.out.println("uri[" + noNeedValidationURI.trim() + "]");
		}
		System.out.println();
	}


	@Override
	public void destroy() {
				
	}
}
