package com.t29.happybook.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class BaseDao {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	void open() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=HappyBookDB", "sa", "sqlpass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	int update(String sql, Object... params){				
		try {
			open();
			pstmt = conn.prepareStatement(sql);					 
			for(int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}			
			return pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close();
		}
	}
	
	void close() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
