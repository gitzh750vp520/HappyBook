<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>鼎育图书网</title>
    <link type="text/css" rel="stylesheet" href="/hb/css/styles.css"/>
  </head>  
  <body>
    <div id="container">
    	<%@ include file="/WEB-INF/jsp/inc/header.jsp" %> 	
    	<div id="main">
    		<div class="section-left">
    			<%@ include file="/WEB-INF/jsp/inc/categoryList.jsp" %>
    		</div>
    		<div class="section-right">
    		<c:if test="${not empty cart }">
    			<h3 align="center" style="margin-bottom: 20px; ">您选购的商品如下：</h3>
    			<table  align="center"  cellpadding="0" cellspacing="0">
    				<tr>
    					<td class="header" width="200">书名</td>
    					<td class="header"  width="60">单价</td>
    					<td class="header"  width="60">购物数量</td>
    					<td class="header"  width="60">小计</td>
    				</tr>
    			<c:forEach items="${ cart }" var="item">
    				<tr>
    					<td>《${ item.name }》</td>
    					<td>￥${ item.price }</td>
    					<td>${ item.count }本</td>
    					<td>￥${ item.total }</td>
    				</tr>
    			</c:forEach>	
    				<tr>
    					<td colspan="4" class="header" style="text-align: right;">总计：￥${ sum }</td>
    				</tr>
    			</table>	
    			<div style="text-align: center; font-size: 14px; line-height: 40px;">
    				<a href="#" style="text-decoration: underline;">去结账</a>&nbsp;&nbsp;
    				<a href="/hb/doIndex.jsp" style="text-decoration: underline;">继续购物</a>
    			</div>
    		</c:if>
    		<c:if test="${empty cart }">
    			<h3>您还没有选购任何商品！请可以<a href="/hb/doIndex.jsp"  style="text-decoration: underline;">先购物</a></h3>
    		</c:if>
    		</div>
    		<div class="cf"></div>   	
    	</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>