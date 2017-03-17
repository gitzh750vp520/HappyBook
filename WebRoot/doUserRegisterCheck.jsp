<%@ page language="java" import="java.util.*, com.t29.happybook.service.*, com.t29.happybook.entity.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String loginId = request.getParameter("loginId");
	String loginPsw = request.getParameter("loginPsw");
	String name = request.getParameter("name");
	String code = request.getParameter("code");
	
	// 当用户看到验证码的时候，证明访问了code.jsp。在code.jsp执行的过程中
	// 已经将随机4位数字的字符串以“rand”作为属性名，存储到了session作用域中
	String rand = (String)session.getAttribute("rand");
	
	if(!code.equals(rand)) {
		request.setAttribute("tipRegister", "<span class='fail'>用户信息注册失败：验证码错误！</span>");
		request.getRequestDispatcher("/doUserRegisterForm.jsp").forward(request, response);
		return;
	}
	

	User user = new User();
	user.setLoginId(loginId);
	user.setLoginPsw(loginPsw);
	user.setName(name);
	
	UserService userService = new UserService();	
	String tip = "<span class='fail'>用户信息注册失败：账号已经存在！</span>";
	if(userService.regist(user)) {
		tip = "<span class='success'>用户信息注册成功！</span>";
	}	
	
	request.setAttribute("tipRegister", tip);
	request.getRequestDispatcher("/doUserRegisterForm.jsp").forward(request, response);

%>