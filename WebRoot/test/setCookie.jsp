<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%
	// 创建了一个“小段信息”对象：信息名为"myKey"，信息值为"myValue"
	Cookie c = new Cookie("myKey", "myValue");
	
	Cookie c2 = new Cookie("myKey2", "myValue2");
	c2.setPath("/hb");
	c2.setMaxAge(0);
		
	// 向“响应对象”中添加cookie
	response.addCookie(c);
	response.addCookie(c2);
	
	response.sendRedirect("/hb/getCookie.jsp");
%>