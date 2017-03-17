package com.t29.happybook.service;

import com.t29.happybook.dao.UserDao;
import com.t29.happybook.entity.User;

public class UserService {
	
	private UserDao userDao = new UserDao();

	/**
	 * 实现注册新用户信息的方法
	 * @param user 要注册的用户信息的User对象（必须有loginId、loginPsw、name）
	 * @return 如果注册成功，则返回true；如果注册失败（一定是因为loginId已经存在），则返回false。
	 */
	public boolean regist(User user) {
		// 1、根据要注册的user的loginId查找用户是否已经存在，如果存在，直接返回false；		
		if(userDao.getUserByLoginId(user.getLoginId()) != null) {
			return false;
		}		
		// 2、如果不存在，则存储要注册的user的信息。返回true
		userDao.add(user);
		return true;		
	}
	/**
	 * 实现用户登录验证的方法
	 * @param loginId 要验证的账号
	 * @param loginPsw 要验证的密码
	 * @return 验证成功后的User对象，如果验证失败，则返回null。
	 */
	public User login(String loginId, String loginPsw) {
		User user = userDao.getUserByLoginId(loginId);
		if(user != null && user.getLoginPsw().equals(loginPsw) && user.isStatus()) {
			return user;
		}
		return null;
	}
	
}
