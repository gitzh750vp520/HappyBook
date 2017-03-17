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
				<h2>编辑图书信息</h2>
				<form action="/hb/mgr/book/modify" method="post" enctype="multipart/form-data">	
					<input type="hidden" name="bid" value="${book.id }" />
					<input type="hidden" name="oldPhoto" value="${book.photo }" />
					<p>图书书名：<input type="text" name="btitle" value="${book.title }"  /></p>
					<p>图书作者：<input type="text" name="bauthor" value="${book.author }"  /></p>
					<p>图书分类：
						<select name="cid">
						<c:forEach items="${ allCategories }" var="c">
							<option value="${c.id }"${ c.id eq book.category.id ? ' selected ' : '' }>${c.name }</option>						
						</c:forEach>
						</select>
					</p>
					<p>图书售价：<input type="text" name="bprice" value="${book.price }" /></p>
					<p>图书出版社：<input type="text" name="bpublisher" value="${book.publisher }"  /></p>  
					<p>图书图片：<img src="/hb/photo/${book.photo }" width="90" height="120" /></p> 
					<p>选择图片：<input type="file" name="bphoto"  /></p>    				 				
					<p><input type="submit" value=" 修 改 "  />&nbsp;<span class="success">${ tipBookModify }</span></p>
				</form>
			</div>
			<div class="section-right">				
			</div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>