<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/autoLogin.jsp" %>
<%
	
	
	request.setAttribute("sum", ((Cart)session.getAttribute("cart")).getSum());	
	request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
%>
