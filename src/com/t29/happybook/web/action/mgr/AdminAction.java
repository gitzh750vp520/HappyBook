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

	// ʵ�ֹ���Աȥ��¼����ͼ�Ĵ�����
	public void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/jsp/mgr/admin-login.jsp").forward(request, response);
	}
	
	// ʵ�ֹ���Ա��¼��֤�Ĵ�����
	public void loginCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		String loginId = request.getParameter("loginId");
		String loginPsw = request.getParameter("loginPsw");		
		
		Admin admin = adminService.loginCheck(loginId, loginPsw);
		
		if(admin == null) {
			request.setAttribute("tip", "����Ա��¼ʧ�ܣ��˺Ż��������");
			request.getRequestDispatcher("/mgr/admin/loginForm").forward(request, response);
		} else {
			//��¼��֤ͨ��
			request.getSession().setAttribute("currAdmin", admin);
			response.sendRedirect("/hb/mgr/admin/home");
		}
	}
	
	/* ʵ�ֹ���Ա��ҳ����Ĵ����� */
	public void home(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		List<Admin> adms = adminService.getAllAdminsNormal();
		request.setAttribute("admins", adms);
		request.getRequestDispatcher("/WEB-INF/jsp/mgr/admin-home.jsp").forward(request, response);		
	}

	/* ʵ�ֹ���Աע���Ĵ����� */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		request.getSession().invalidate();	
		response.sendRedirect("/hb/mgr/admin/loginForm");
	}

	/* ʵ�ֳ�������Ա�޸���ͨ����Ա״̬�Ĵ����� */
	public void modifyStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		String loginId = request.getParameter("loginId");	
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		
		
		// Լ���������޸Ĺ���Ա״̬�ķ����󣬻᷵��һ�������������int����ֵ��
		// �����Ϊ0�������޸�ʧ�ܣ�ԭ��Ϊ������loginId������	
		// �����Ϊ1�������޸�ʧ�ܣ�Ը��Ϊ������loginId�ǳ�������Ա��loginId
		// �����λ2�������޸ĳɹ���
		int result = adminService.modifyStatus(loginId, status);
		
		String tip = "";
		if(result == 0) {
			tip = "<p class='fail'>�޸Ĺ���Ա��״̬ʧ�ܣ�û���ҵ��˺�Ϊ��" + loginId + "���Ĺ���Ա��Ϣ</p>";
		} else if(result == 1) {
			tip = "<p class='fail'>�޸Ĺ���Ա��״̬ʧ�ܣ������޸ĳ�������Ա��" + loginId + "����״̬</p>";
		} else if(result == 2) {
			tip = "<p class='success'>�˺�Ϊ��"+loginId+"���Ĺ���Ա��״̬�޸ĳɹ���</p>";
		}
		
		request.setAttribute("tipModifyStatus", tip);
		request.getRequestDispatcher("/mgr/admin/home").forward(request, response);
	}
	
	/* ʵ�ֳ�������Աע������ͨ����Ա�Ĵ����� */
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		
		Admin admin = new Admin();
		admin.setLoginId(loginId);
		admin.setLoginPsw("000000");
		admin.setType("��ͨ����Ա");
		admin.setStatus(true);
		admin.setName(name);
		
		
		String tipAdminSave = "<p class='fail'>ע������ͨ����Աʧ�ܣ��˺��Ѿ����ڣ�</p>";
		if(adminService.save(admin)) {
			tipAdminSave = "<p class='success'>ע���¹���Ա��Ϣ�ɹ�������ʾ�¹���Ա��ʱ�޸�����</p>";
		}
		
		request.setAttribute("tipAdminSave", tipAdminSave);
		request.getRequestDispatcher("/mgr/admin/home").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
