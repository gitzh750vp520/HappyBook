<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>鼎育图书网</title>
    <link type="text/css" rel="stylesheet" href="/hb/css/styles.css"/>
    <script type="text/javascript">
    	function changeCode() {
    		var img = document.getElementById("img");
    		img.src = "code.jsp?r=" + Math.random();    		
    	}    
    </script>
  </head>  
  <body>
    <div id="container">
    	<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>
    	<div id="main">
    		<div class="box" id="register">
    			<div class="title">新用户注册</div>
<form action="/hb/doUserRegisterCheck.jsp" method="post" style="margin: 10px;">
	<table cellspacing="0" class="no-border">
    	<tr>
    		<td style="text-align: right;">登录账号：</td>
    		<td><input type="text" name="loginId" class="txt" value="${ param.loginId }" /></td>
    	</tr>
    	<tr>
    		<td style="text-align: right;">登录密码：</td>
    		<td><input type="password" name="loginPsw" class="txt" value="${ param.loginPsw }" /></td>
    	</tr>
    	<tr>
    		<td style="text-align: right;">再次输入密码：</td>
    		<td><input type="password" name="loginPsw2" class="txt" value="" /></td>
    	</tr>
    	<tr>
    		<td style="text-align: right;">真实姓名：</td>
    		<td><input type="text" name="name" class="txt" value="${ param.name }" /></td>
    	</tr>
    	<tr>
    		<td style="text-align: right;">验证码：</td>
    		<td><input type="text" name="code" class="txt" /></td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td><img id="img" src="/hb/code.jsp" onclick="changeCode()" />&nbsp;&nbsp;看不清？<a href="javascript:changeCode();" style="color: #64A26F;">换张图</a></td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td><input type="submit" value=" 注  册 " class="btn" />&nbsp;&nbsp;${ tipRegister }</td>
    	</tr>
    </table>
</form>
    		</div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;鼎育教育</p></div>
	</div>
  </body>
</html>