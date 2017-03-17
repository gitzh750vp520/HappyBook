<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>

<html>
  <head>
    <title>Cookie获取</title>   
  </head>  
  <body>
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