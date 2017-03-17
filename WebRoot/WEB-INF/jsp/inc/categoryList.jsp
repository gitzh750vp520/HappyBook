<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	CategoryService categoryService = new CategoryService();
	List<Category> allCategories = categoryService.getAllCategories();
	pageContext.setAttribute("allCategories", allCategories);
%>
<div class="box-left">
	<div class="box-title">分类畅销榜</div>
	<div class="box-content">
		<p>·<a href="/hb/doIndex.jsp?cname=全部">全部</a></p>
	<c:forEach items="${ allCategories }" var="c">
		<p>·<a href="/hb/doIndex.jsp?cname=${c.name }">${c.name }</a></p>
	</c:forEach>			
	</div>
</div>
    	
    		