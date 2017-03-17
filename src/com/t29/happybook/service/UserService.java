package com.t29.happybook.service;

import com.t29.happybook.dao.UserDao;
import com.t29.happybook.entity.User;

public class UserService {
	
	private UserDao userDao = new UserDao();

	/**
	 * ʵ��ע�����û���Ϣ�ķ���
	 * @param user Ҫע����û���Ϣ��User���󣨱�����loginId��loginPsw��name��
	 * @return ���ע��ɹ����򷵻�true�����ע��ʧ�ܣ�һ������ΪloginId�Ѿ����ڣ����򷵻�false��
	 */
	public boolean regist(User user) {
		// 1������Ҫע���user��loginId�����û��Ƿ��Ѿ����ڣ�������ڣ�ֱ�ӷ���false��		
		if(userDao.getUserByLoginId(user.getLoginId()) != null) {
			return false;
		}		
		// 2����������ڣ���洢Ҫע���user����Ϣ������true
		userDao.add(user);
		return true;		
	}
	/**
	 * ʵ���û���¼��֤�ķ���
	 * @param loginId Ҫ��֤���˺�
	 * @param loginPsw Ҫ��֤������
	 * @return ��֤�ɹ����User���������֤ʧ�ܣ��򷵻�null��
	 */
	public User login(String loginId, String loginPsw) {
		User user = userDao.getUserByLoginId(loginId);
		if(user != null && user.getLoginPsw().equals(loginPsw) && user.isStatus()) {
			return user;
		}
		return null;
	}
	
}
