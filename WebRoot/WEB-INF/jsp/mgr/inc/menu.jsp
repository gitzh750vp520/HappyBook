<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<div id="info">${ currAdmin.name }【账号：${currAdmin.loginId }，级别：${ currAdmin.type }】，您好！您已成功登录后台，请谨慎操作&nbsp;&nbsp;
<a href="/hb/mgr/admin/logout">注销</a></div>
<div class="menu">
	<ul>
		<li><a href="/hb/mgr/admin/home">管理员主页</a></li>
		<li><a href="/hb/mgr/category/manage">图书分类管理</a></li>
		<li><a href="/hb/mgr/book/manage">图书管理</a></li>
		<li><a href="#">购书订单管理</a></li>
	</ul>	
</div>