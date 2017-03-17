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
				<h2>图书分类信息</h2>
				<table class="table" cellspacing="0">
			    	<tr>
			    		<td class="header" width="200">图书分类</td>
			    		<td class="header" width="60">操作</td>
			    	</tr>
			    <c:forEach items="${ categories }" var="c"> 
			    	<tr>
			    		<td>${c.name }</td>
			    		<td><a href="/hb/mgr/category/delete?id=${c.id }">删除</a></td>
			    	</tr>
			    </c:forEach>
			    </table>
			    <div style="height: 40px; line-height: 40px; font-size:12px;">
			    	<a href="/hb/mgr/category/manage?pageNO=1">首页</a>&nbsp;
			    	<a href="/hb/mgr/category/manage?pageNO=${ paging.pagePrev }">上一页</a>&nbsp;
			    	<a href="/hb/mgr/category/manage?pageNO=${ paging.pageNext }">下一页</a>&nbsp;
			    	<a href="/hb/mgr/category/manage?pageNO=${ paging.pageTotal }">尾页</a>&nbsp;
			    	当前第${ paging.pageNO }页/共计${ paging.pageTotal }页&nbsp;共计${ paging.countTotal }条
			    </div>  
			    <p>${ tip }</p>
			</div>
			<div class="section-right">
				<h2>添加分类信息</h2>
				<form action="/hb/mgr/category/save" method="POST">				
				<p>分类名称：<input type="text" name="categoryName"  />
					<input type="submit" value=" 保 存 "  /></p>${ msg }
			    </form>	
			</div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>
