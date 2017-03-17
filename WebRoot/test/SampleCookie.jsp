<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Cookie示例页面</title>   
  </head>  
  <body>
  	<a href="setCookie.jsp">设置Cookie信息</a>
  	<% 
	Cookie[] cookieArr = request.getCookies();
	if(cookieArr != null) {
		for(Cookie each : cookieArr) {
			out.println(each.getName() + " ===== " + each.getValue() + "<br /><br />");
		}				
	}
%> 
  </body>
</html>