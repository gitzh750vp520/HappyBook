<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书网后台管理系统--用户登录</title>
    <link type="text/css" rel="stylesheet" href="/hb/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>鼎育教育--图书网后台管理系统</h1></div>
    	<div id="main">
    		<h2>用户登录</h2>
			<form action="/hb/mgr/admin/loginCheck" method="post">
				<p>登录账号：<input name="loginId" type="text" /></p>
				<p>登录密码：<input name="loginPsw" type="password" /></p>
				<p><input type="submit" value=" 登  录 " />&nbsp;<label class="fail">${ tip }</label></p>
			</form>
    	</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>