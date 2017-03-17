package com.t29.happybook.web.action.mgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.t29.happybook.entity.Admin;
import com.t29.happybook.service.AdminService;

@SuppressWarnings("serial")
public class AdminAction extends HttpServlet {
	
	private AdminService adminService = new AdminService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		
		// uri : /hb/mgr/admin/xxx
		String uri = request.getRequestURI();
		
		if(uri.equals("/hb/mgr/admin/loginForm")) {
			loginForm(request, response);
		} else if(uri.equals("/hb/mgr/admin/loginCheck")) {				
			loginCheck(request, response);
		} else if(uri.equals("/hb/mgr/admin/home")) {				
			home(request, response);
		} else if(uri.equals("/hb/mgr/admin/logout")) {
			logout(request, response);
		} else if(uri.equals("/hb/mgr/admin/modifyStatus")) {
			modifyStatus(request, response);
		} else if(uri.equals("/hb/mgr/admin/register")) {
			register(request, response);
		} else {
			if(request.getSession().getAttribute("currAdmin") != null) {
				home(request, response);
			}else {
				loginForm(request, response);
			}
		}
	}	

	// 实现管理员去登录表单视图的处理方法
	public void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/jsp/mgr/admin-login.jsp").forward(request, response);
	}
	
	// 实现管理员登录验证的处理方法
	public void loginCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		String loginId = request.getParameter("loginId");
		String loginPsw = request.getParameter("loginPsw");		
		
		Admin admin = adminService.loginCheck(loginId, loginPsw);
		
		if(admin == null) {
			request.setAttribute("tip", "管理员登录失败：账号或密码错误！");
			request.getRequestDispatcher("/mgr/admin/loginForm").forward(request, response);
		} else {
			//登录验证通过
			request.getSession().setAttribute("currAdmin", admin);
			response.sendRedirect("/hb/mgr/admin/home");
		}
	}
	
	/* 实现管理员首页管理的处理器 */
	public void home(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		List<Admin> adms = adminService.getAllAdminsNormal();
		request.setAttribute("admins", adms);
		request.getRequestDispatcher("/WEB-INF/jsp/mgr/admin-home.jsp").forward(request, response);		
	}

	/* 实现管理员注销的处理器 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		request.getSession().invalidate();	
		response.sendRedirect("/hb/mgr/admin/loginForm");
	}

	/* 实现超级管理员修改普通管理员状态的处理器 */
	public void modifyStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		String loginId = request.getParameter("loginId");	
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		
		
		// 约定：调用修改管理员状态的方法后，会返回一个代表“结果”的int返回值。
		// 当结果为0：代表修改失败，原因为给定的loginId不存在	
		// 当结果为1：代表修改失败，愿因为给定的loginId是超级管理员的loginId
		// 当结果位2：代表修改成功！
		int result = adminService.modifyStatus(loginId, status);
		
		String tip = "";
		if(result == 0) {
			tip = "<p class='fail'>修改管理员的状态失败：没有找到账号为【" + loginId + "】的管理员信息</p>";
		} else if(result == 1) {
			tip = "<p class='fail'>修改管理员的状态失败：不能修改超级管理员【" + loginId + "】的状态</p>";
		} else if(result == 2) {
			tip = "<p class='success'>账号为【"+loginId+"】的管理员的状态修改成功！</p>";
		}
		
		request.setAttribute("tipModifyStatus", tip);
		request.getRequestDispatcher("/mgr/admin/home").forward(request, response);
	}
	
	/* 实现超级管理员注册新普通管理员的处理器 */
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		
		Admin admin = new Admin();
		admin.setLoginId(loginId);
		admin.setLoginPsw("000000");
		admin.setType("普通管理员");
		admin.setStatus(true);
		admin.setName(name);
		
		
		String tipAdminSave = "<p class='fail'>注册新普通管理员失败：账号已经存在！</p>";
		if(adminService.save(admin)) {
			tipAdminSave = "<p class='success'>注册新管理员信息成功！请提示新管理员及时修改密码</p>";
		}
		
		request.setAttribute("tipAdminSave", tipAdminSave);
		request.getRequestDispatcher("/mgr/admin/home").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
