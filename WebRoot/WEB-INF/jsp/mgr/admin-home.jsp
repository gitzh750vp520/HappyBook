<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="/hb/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>鼎育教育--图书网后台管理系统</h1></div>
    	<%@ include file="/WEB-INF/jsp/mgr/inc/menu.jsp" %>
    	<div id="main">
			<div class="section-left">
				<h3>个人详细信息</h3>
				<ul style="margin-left: 20px; list-style-type: none; line-height: 180%;">
					<li>账号：${ currAdmin.loginId }</li>
					<li>姓名：${ currAdmin.name }</li>
					<li>级别：${ currAdmin.type }</li>
				</ul>			
			</div>
			<div class="section-right">
			<c:if test="${ currAdmin.type eq '超级管理员' }">
				<h3>普通管理员信息列表</h3>
				<table class="table" cellspacing="0" style="font-size: 12px;">
			    	<tr>
			    		<td class="header" width="100">账号</td>
			    		<td class="header" width="60">姓名</td>
			    		<td class="header" width="60">级别</td>
			    		<td class="header" width="60">状态</td>
			    		<td class="header" width="60">操作</td>
			    	</tr>
			    <c:forEach items="${ admins }" var="ad">		    	
			    	<tr>
			    		<td>${ ad.loginId }</td>
			    		<td>${ ad.name }</td>
			    		<td>${ ad.type }</td>
			    		<td>${ ad.status ? '可用' : '禁用中' }</td>
			    		<td><a href="/hb/mgr/admin/modifyStatus?loginId=${ ad.loginId }&status=${ !ad.status }">${ ad.status ? "停用" : "启用" } </a></td>
			    	</tr>
			    </c:forEach>
				</table>
				${tipModifyStatus }
				<h3 style=" margin-top: 50px;">注册新普通管理员</h3>
				<form action="/hb/mgr/admin/register" method="POST" style="font-size: 12px;">
					<p>新管理员账号：<input type="text" name="loginId" /></p>
					<p>新管理员密码：000000 </p>
					<p>新管理员姓名：<input type="text" name="name" /></p>
					<p><input type="submit" value="  注 册  " /></p>
					${tipAdminSave }
				</form>
			</c:if>
			</div>						
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>