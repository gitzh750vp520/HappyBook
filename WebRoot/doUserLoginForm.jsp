<% 	
	Cookie[] cookieArr = request.getCookies();
	if(cookieArr != null) {
		for(Cookie each : cookieArr) {
			if(each.getName().equals("reLoginId")) {				
				request.setAttribute("reLoginId", each.getValue());
				request.setAttribute("status", " checked");
			}
			
			if(each.getName().equals("reLoginPsw")) {				
				request.setAttribute("reLoginPsw", each.getValue());
			}
		}				
	}
	request.getRequestDispatcher("/WEB-INF/jsp/user-login.jsp").forward(request, response);
%>