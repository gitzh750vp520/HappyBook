package com.t29.happybook.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.t29.happybook.entity.Admin;

public class AdminDao extends BaseDao {

	/**
	 * 根据指定的loginId从AdminInfo表中查询管理员的详细信息
	 * 
	 * @param loginId 指定的登录账号
	 * @return Admin对象，如果没查找，则返回null；如果查到了，则返回封装了所有字段的Admin对象
	 */
	public Admin getAdminByLoginId(String loginId) {
		Admin admin = null;
		try {
			open();
			String sql = "select AID, ALoginID, ALoginPsw, AName, AType, AStatus from AdminInfo where ALoginID = ?";			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt("AID"));
				admin.setLoginId(rs.getString("ALoginID"));
				admin.setLoginPsw(rs.getString("ALoginPsw"));
				admin.setName(rs.getString("AName"));
				admin.setType(rs.getString("AType"));
				admin.setStatus(rs.getBoolean("AStatus"));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
		
		return admin;
	}

	/**
	 * 根据指定的管理员类型（type）查找对应的管理员信息
	 * type的可能值为：
	 * （1）所有类型
	 * （2）超级管理员
	 * （3）普通管理员
	 * 
	 * @param type 指定的管理员类型
	 * @return 对应类型的所有管理员信息列表（Admin类的泛型List）
	 */
	public List<Admin> getAllAdminsByType(String type) {
		List<Admin> admins = new ArrayList<Admin>();
		try {
			open();
			String sql = "select AID, ALoginID, ALoginPsw, AName, AType, AStatus from AdminInfo";			
			if(type.equals("超级管理员") || type.equals("普通管理员")) {
				sql += " where AType = '" + type + "'";
			}
			//System.out.println("\n【getAllAdminsByType的SQL语句】" + sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getInt("AID"));
				admin.setLoginId(rs.getString("ALoginID"));
				admin.setLoginPsw(rs.getString("ALoginPsw"));
				admin.setName(rs.getString("AName"));
				admin.setType(rs.getString("AType"));
				admin.setStatus(rs.getBoolean("AStatus"));
				admins.add(admin);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
		return admins;
	}

	/**
	 * 根据给定amdin的loginId，更新ALoginPsw、AName、AStatus字段
	 * 
	 * @param admin
	 */
	public void update(Admin admin) {
		try {
			open();
			String sql = "update AdminInfo set ALoginPsw = ?, AName = ?, AStatus = ? where ALoginID = ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getLoginPsw());
			pstmt.setString(2, admin.getName());
			pstmt.setBoolean(3, admin.getStatus());
			pstmt.setString(4, admin.getLoginId());
			pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
	}

	public void add(Admin admin) {
		try {
			open();
			String sql = "insert into AdminInfo (ALoginID, ALoginPsw, AName, AType, AStatus) values (?, ?, ?, ?, ?)";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getLoginId());
			pstmt.setString(2, admin.getLoginPsw());
			pstmt.setString(3, admin.getName());
			pstmt.setString(4, admin.getType());
			pstmt.setBoolean(5, admin.getStatus());
			pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
	}

}
