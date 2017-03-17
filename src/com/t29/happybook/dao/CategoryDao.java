package com.t29.happybook.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.t29.happybook.entity.Category;

public class CategoryDao extends BaseDao {

	public List<Category> getAllCategories() {
		List<Category> list = new ArrayList<Category>();
		try {
			open();
			pstmt = conn.prepareStatement("select CID, CName from CategoryInfo");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("CID"));
				c.setName(rs.getString("CName"));
				list.add(c);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}		
		return list;
	}

	/**
	 * ��ҳ����ͼ�������Ϣ
	 * @param pageNO �ڼ�ҳ
	 * @param pageSize ÿҳ����
	 * @return ��װ��ָ��ҳ�ķ�����Ϣ���б�Category�ķ���List��
	 */
	public List<Category> getCategories(int pageNO, int pageSize) {
		List<Category> list = new ArrayList<Category>();
		try {
			open();
			pstmt = conn.prepareStatement("select top " + pageSize + " CID, CName from CategoryInfo where CID not in(select top " + (pageNO - 1) * pageSize + " CID from CategoryInfo order by CID) order by CID");			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("CID"));
				c.setName(rs.getString("CName"));
				list.add(c);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}		
		return list;
	}	

	public int getCategoryCount() {
		try {
			open();
			pstmt = conn.prepareStatement("select count(*) from CategoryInfo");			
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}		
	}
	
	public void add(String categoryName) {
		try {
			open();
			pstmt = conn.prepareStatement("insert into CategoryInfo (CName) values (?)");
			pstmt.setString(1, categoryName);
			pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}	
	}

	public int delete(int id) {
		try {
			open();
			pstmt = conn.prepareStatement("delete from CategoryInfo where CID = ?");
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
	}
	
	
	public static void main(String[] args) {
		CategoryDao dao = new CategoryDao();
		List<Category> list = dao.getCategories(2, 2);
		System.out.println("\n��ǰҳ�������ݵ�����Ϊ��" + list.size());
		for(Category c : list) {
			System.out.println(c.getId() + ", " + c.getName());
		}
	}
}
