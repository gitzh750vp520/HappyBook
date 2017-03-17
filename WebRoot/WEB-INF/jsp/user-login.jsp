<%@ page pageEncoding="UTF-8"%>
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
    		<div class="box" id="register">
    			<div class="title">用户登录</div>
<form action="/hb/doUserLoginCheck.jsp" method="post" style="margin: 10px;">
	<table cellspacing="0" class="no-border">
    	<tr>
    		<td style="text-align: right;">登录账号：</td>
    		<td><input type="text" name="loginId" class="txt" value="${ reLoginId }" /></td>
    	</tr>
    	<tr>
    		<td style="text-align: right;">登录密码：</td>
    		<td><input type="password" name="loginPsw" class="txt" value="${ reLoginPsw }" /></td>
    	</tr>
    	<tr>
    		<td style="text-align: right;">&nbsp;</td>
    		<td><input type="checkbox" name="remember"${ status } />&nbsp;记住账号和密码&nbsp;
    		<input type="checkbox" name="autoLogin" />&nbsp;自动登录
    		</td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td><input type="submit" value=" 登  录 " class="btn" />&nbsp;&nbsp;<span class="fail">${ tipLogin }</span></td>
    	</tr>
    </table>
</form>
    		</div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>