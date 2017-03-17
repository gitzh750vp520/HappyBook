package com.t29.happybook.service;

import java.util.List;

import com.t29.happybook.dao.AdminDao;
import com.t29.happybook.entity.Admin;

public class AdminService {
	
	private AdminDao adminDao = new AdminDao();

	/**
	 * �÷���ʵ�ֹ���Ա��¼��֤�Ĺ���
	 * 
	 * @param loginId Ҫ��֤�ĵ�¼�˺�
	 * @param loginPsw Ҫ��֤�ĵ�¼����
	 * @return ����Ա���������֤ʧ�ܣ��򷵻�null�������֤ͨ�����򷵻ط�װ��Admin�����ֶεĴ������Ա�Ķ���
	 */
	public Admin loginCheck(String loginId, String loginPsw) {
		Admin admin = adminDao.getAdminByLoginId(loginId);	
		if(admin != null && admin.getLoginPsw().equals(loginPsw) && admin.getStatus() == true) {
			return admin;
		}		
		return null;
	}
	
	/**
	 * �÷���ʵ�ֲ������е���ͨ����Ա��Ϣ�Ĺ���
	 * @return
	 */
	public List<Admin> getAllAdminsNormal() {
		return adminDao.getAllAdminsByType("��ͨ����Ա");
	}
	
	// Լ����ʵ���޸Ĺ���Ա״̬�ķ������᷵��һ�������������int����ֵ��
	// �����Ϊ0�������޸�ʧ�ܣ�ԭ��Ϊ������loginId������	
	// �����Ϊ1�������޸�ʧ�ܣ�Ը��Ϊ������loginId�ǳ�������Ա��loginId
	// �����λ2�������޸ĳɹ���
	public int modifyStatus(String loginId, boolean status) {
		Admin adminSearched = adminDao.getAdminByLoginId(loginId);
		if(adminSearched == null) {
			return 0;
		} else if(adminSearched.getType().equals("��������Ա")) {
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
