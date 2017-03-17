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
		System.out.println("admin filter : uri[" + uri + "], �Ƿ��¼[" + hasLogin + "]");
		if(hasLogin || checkNoNeedValidation(uri)) {
			chain.doFilter(req, resp);
		} else {
			request.setAttribute("tip", "���ȵ�¼�ٽ�������������");
			request.getRequestDispatcher("/mgr/admin/loginForm").forward(request, resp);
		}
	}

	// ��鵱ǰ��������uri�Ƿ������ڲ���Ҫ��֤��uri
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
		System.out.println("\nAdminValidationFilter ʵ����... ����Ҫ��֤��URI���������£�");
		for(String noNeedValidationURI : noNeedValidationURIArr) {
			System.out.println("uri[" + noNeedValidationURI.trim() + "]");
		}
		System.out.println();
	}


	@Override
	public void destroy() {
				
	}
}
