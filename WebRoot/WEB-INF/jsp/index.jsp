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
    			<div class="box-right">
    				<div class="box-title">图书信息【分类：${ cond_cname }；书名：${ empty cond_btitle ? "无限制" : cond_btitle }】</div>
    				<div class="paging" style="border-bottom: 1px solid  #64A26F; color: #006666; ">
    					 <span class="fr"><a href="/hb/doIndex.jsp?pageNO=1">首页</a>&nbsp;<a href="/hb/doIndex.jsp?pageNO=${ paging.pagePrev }">上一页</a>&nbsp;<a href="/hb/doIndex.jsp?pageNO=${ paging.pageNext }">下一页</a>&nbsp;<a href="/hb/doIndex.jsp?pageNO=${ paging.pageTotal }">尾页</a>&nbsp;</span>
    					 共有图书${ paging.countTotal }种，分${ paging.pageTotal }页显示，每页显示2个商品
    				</div>
    			<c:forEach items="${ books }" var="b">
    				<div class="box-item">
    					<div class="img-box"><img src="photo/${ b.photo }" /></div>
    					<div class="info-box">
    						<span style="font-size: 14px; "><a href="#">${ b.title }</a></span><br />
							作者：${ b.author }&nbsp;&nbsp;著<br />
							分类：${ b.category.name }<br />
							出版社：${ b.publisher }<br />							
							售价：￥<span style="color: #990000;">${ b.price }</span>		<br />					
    					</div>
    					<a href="doCartAddGoods.jsp?id=${b.id }&title=${b.title }&price=${b.price}" class="btn-buy">加入购物车</a>    					
    					<div class="cf"></div>
    				</div>    
    		 	</c:forEach>
    				<div class="paging">
    					 <span class="fr"><a href="/hb/doIndex.jsp?pageNO=1">首页</a>&nbsp;<a href="/hb/doIndex.jsp?pageNO=${ paging.pagePrev }">上一页</a>&nbsp;<a href="/hb/doIndex.jsp?pageNO=${ paging.pageNext }">下一页</a>&nbsp;<a href="/hb/doIndex.jsp?pageNO=${ paging.pageTotal }">尾页</a>&nbsp;</span>    					
    				</div>
    			</div>
    		</div>
    		<div class="cf"></div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>