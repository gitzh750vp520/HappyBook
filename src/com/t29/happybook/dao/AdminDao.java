package com.t29.happybook.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.t29.happybook.entity.Admin;

public class AdminDao extends BaseDao {

	/**
	 * ����ָ����loginId��AdminInfo���в�ѯ����Ա����ϸ��Ϣ
	 * 
	 * @param loginId ָ���ĵ�¼�˺�
	 * @return Admin�������û���ң��򷵻�null������鵽�ˣ��򷵻ط�װ�������ֶε�Admin����
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
	 * ����ָ���Ĺ���Ա���ͣ�type�����Ҷ�Ӧ�Ĺ���Ա��Ϣ
	 * type�Ŀ���ֵΪ��
	 * ��1����������
	 * ��2����������Ա
	 * ��3����ͨ����Ա
	 * 
	 * @param type ָ���Ĺ���Ա����
	 * @return ��Ӧ���͵����й���Ա��Ϣ�б�Admin��ķ���List��
	 */
	public List<Admin> getAllAdminsByType(String type) {
		List<Admin> admins = new ArrayList<Admin>();
		try {
			open();
			String sql = "select AID, ALoginID, ALoginPsw, AName, AType, AStatus from AdminInfo";			
			if(type.equals("��������Ա") || type.equals("��ͨ����Ա")) {
				sql += " where AType = '" + type + "'";
			}
			//System.out.println("\n��getAllAdminsByType��SQL��䡿" + sql);
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
	 * ���ݸ���amdin��loginId������ALoginPsw��AName��AStatus�ֶ�
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
