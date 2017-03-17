<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<div id="tool-bar">&nbsp;&nbsp; 欢迎光临鼎育图书网，
<c:if test="${ empty currUser }">
	[<a href="/hb/doUserLoginForm.jsp">请登录</a>]&nbsp;[<a href="/hb/doUserRegisterForm.jsp">免费注册</a>]
</c:if>
<c:if test="${not empty currUser }">	
	${ currUser.name }[积分：${currUser.point }分]，<a href="#">个人中心</a>&nbsp;<a href="/hb/doUserLogout.jsp">注销</a>
</c:if>
	&nbsp;&nbsp;&nbsp;<a href="/hb/doIndex.jsp">首页</a>&nbsp;|&nbsp;<a href="/hb/doCart.jsp">购物车</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;<a href="#">帮助</a></div>
	<h1>鼎育图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
</div>
<form id="search-bar" action="/hb/doIndex.jsp" method="post">
	书名：<input type="text" class="txt" name="btitle" value="${cond_btitle }" />
	<input id="search-btn" type="submit" value=" 搜索图书 " />
</form>
    	