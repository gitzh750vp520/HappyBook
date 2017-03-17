<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	String title = request.getParameter("title");
	title = new String( title.getBytes("iso8859-1") , "UTF-8");
	double price = Double.parseDouble(request.getParameter("price"));
	
	Cart cart = (Cart)session.getAttribute("cart");	
	cart.add(new CartItem(id, title, price));	
	
	response.sendRedirect("/hb/doCart.jsp");
%>
