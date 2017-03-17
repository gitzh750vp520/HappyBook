<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String loginId = request.getParameter("loginId");
	String loginPsw = request.getParameter("loginPsw");
	
	UserService userService = new UserService();	
	User user = userService.login(loginId, loginPsw);
	if(user != null) {	
		int maxAge = 0;	
		if(request.getParameter("autoLogin") != null) {
			maxAge = 2 * 7 * 24 * 3600;
			Cookie c = new Cookie("autoLogin", "autoLogin");
			c.setMaxAge(maxAge);
			response.addCookie(c);
		}
		
		if(request.getParameter("remember") != null) { 
			//用户勾选了记住账号和密码的复选框
			maxAge = 2 * 7 * 24 * 3600;			
		} 
		
		Cookie c1 = new Cookie("reLoginId", loginId);
		Cookie c2 = new Cookie("reLoginPsw", loginPsw);
		c1.setMaxAge(maxAge);
		c2.setMaxAge(maxAge);
		
		response.addCookie(c1);
		response.addCookie(c2);
		
		session.setAttribute("currUser", user);
		response.sendRedirect("/hb/doIndex.jsp");
	} else {	
		request.setAttribute("tipLogin", "登录失败：账号或者密码错误，或者被禁用！");
		request.getRequestDispatcher("/doUserLoginForm.jsp").forward(request, response);		
	}
%>