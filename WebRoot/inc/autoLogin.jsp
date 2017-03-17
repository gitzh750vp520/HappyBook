<%@ page language="java" import="java.util.*, com.t29.happybook.web.tool.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<% 	
	if(session.getAttribute("currUser") == null) {
	
		boolean autoLogin = false;
		String loginId = null;
		String loginPsw = null;
		Cookie[] cookieArr = request.getCookies();
		if(cookieArr != null) {
			for(Cookie each : cookieArr) {
				if(each.getName().equals("autoLogin")) {				
					autoLogin = true;
				}
				
				if(each.getName().equals("reLoginId")) {				
					loginId = each.getValue();
					
				}
				
				if(each.getName().equals("reLoginPsw")) {				
					loginPsw = each.getValue();
				}
			}				
		}
		
		if(autoLogin) {
			UserService userService = new UserService();
			User user = userService.login(loginId, loginPsw);
			if(user !=null ) {
				session.setAttribute("currUser", user);
			}
		}
	}	
%>