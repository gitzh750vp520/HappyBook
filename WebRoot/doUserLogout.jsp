<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%
	session.invalidate();
	Cookie c = new Cookie("autoLogin", "autoLogin");
	c.setMaxAge(0);
	response.addCookie(c);
	response.sendRedirect("/hb/doUserLoginForm.jsp");
	
%>