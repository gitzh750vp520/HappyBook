package com.t29.happybook.dao;

import java.sql.SQLException;

import com.t29.happybook.entity.User;

public class UserDao extends BaseDao {

	/**
	 * ����ָ����loginId�������û���ϸ��Ϣ��
	 * @param loginId ָ����loginId
	 * @return ���ҵ����û���ϸ��Ϣ������User��װ�����û�в��ҵ����򷵻�null��
	 */
	public User getUserByLoginId(String loginId) {
		User user = null;
		try {
			open();
			pstmt = conn.prepareStatement("select UID, ULoginID, ULoginPsw, UName, UBirthday, UPoint, UStatus, URegistTime from UserInfo where ULoginID = ?");
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("UID"));
				user.setLoginId(rs.getString("ULoginID"));
				user.setLoginPsw(rs.getString("ULoginPsw"));
				user.setName(rs.getString("UName"));
				user.setBirthday(rs.getString("UBirthday"));
				user.setPoint(rs.getInt("UPoint"));
				user.setStatus(rs.getBoolean("UStatus"));
				user.setRegistTime(rs.getString("URegistTime"));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
		return user;
	}
	
	/**
	 * ��UserInfo�������һ���û���Ϣ
	 * @param user ��װ���û���Ϣ��User���󣨱���Ҫ�ṩloginId��loginPsw��name��
	 */
	public int add(User user) {		
		try {
			open();
			pstmt = conn.prepareStatement("insert into UserInfo (ULoginID, ULoginPsw, UName) values (?, ?, ?)");
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getLoginPsw());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
	}
	
}
