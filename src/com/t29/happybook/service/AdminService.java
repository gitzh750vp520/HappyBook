package com.t29.happybook.service;

import java.util.List;

import com.t29.happybook.dao.AdminDao;
import com.t29.happybook.entity.Admin;

public class AdminService {
	
	private AdminDao adminDao = new AdminDao();

	/**
	 * 该方法实现管理员登录验证的功能
	 * 
	 * @param loginId 要验证的登录账号
	 * @param loginPsw 要验证的登录密码
	 * @return 管理员对象。如果验证失败，则返回null；如果验证通过，则返回封装了Admin所有字段的代表管理员的对象
	 */
	public Admin loginCheck(String loginId, String loginPsw) {
		Admin admin = adminDao.getAdminByLoginId(loginId);	
		if(admin != null && admin.getLoginPsw().equals(loginPsw) && admin.getStatus() == true) {
			return admin;
		}		
		return null;
	}
	
	/**
	 * 该方法实现查找所有的普通管理员信息的功能
	 * @return
	 */
	public List<Admin> getAllAdminsNormal() {
		return adminDao.getAllAdminsByType("普通管理员");
	}
	
	// 约定：实现修改管理员状态的方法，会返回一个代表“结果”的int返回值。
	// 当结果为0：代表修改失败，原因为给定的loginId不存在	
	// 当结果为1：代表修改失败，愿因为给定的loginId是超级管理员的loginId
	// 当结果位2：代表修改成功！
	public int modifyStatus(String loginId, boolean status) {
		Admin adminSearched = adminDao.getAdminByLoginId(loginId);
		if(adminSearched == null) {
			return 0;
		} else if(adminSearched.getType().equals("超级管理员")) {
			return 1;
		} else {
			adminSearched.setStatus(status);
			adminDao.update(adminSearched);
			return 2;
		}
	}
	
	
	public boolean save(Admin admin) {
		if(adminDao.getAdminByLoginId(admin.getLoginId()) != null) {
			return false;
		}		
		adminDao.add(admin);
		return true;
	}
	
	public static void main(String[] args) {
		AdminService ser = new AdminService();
		System.out.println(ser.loginCheck("lisi", "444444"));
	}
	
}
