package com.t29.happybook.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.t29.happybook.entity.Book;

public class BookDao extends BaseDao {
	
	/* 查询所有图书信息的方法，返回一个封装了所有图书信息的泛型列表 */
	public List<Book> getAllBooks() {
		List<Book> list = new ArrayList<Book>();		
		try {
			open();
			String sqlSelect = "select BID, BTitle, BAuthor, BPrice, BPublisher, BPhoto, CID, CName from BookInfo left join CategoryInfo on (BCategoryID = CID)";
			pstmt = conn.prepareStatement(sqlSelect);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("BID"));
				b.setTitle(rs.getString("BTitle"));
				b.setAuthor(rs.getString("BAuthor"));
				b.setPrice(rs.getDouble("BPrice"));
				b.setPublisher(rs.getString("BPublisher"));
				b.setPhoto(rs.getString("BPhoto"));
				b.getCategory().setId(rs.getInt("CID"));
				b.getCategory().setName(rs.getString("CName"));
				list.add(b);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}		
		return list;
	}

	/* 分页查找图书信息（无条件） */
	public List<Book> getBooks(int pageNO, int pageSize) {	
		List<Book> list = new ArrayList<Book>();		
		try {
			open();
			String sqlSelect = "select top " + pageSize + " BID, BTitle, BAuthor, BPrice, BPublisher, BPhoto, CID, CName from BookInfo left join CategoryInfo on (BCategoryID = CID) where BID not in (select top " + ( pageNO - 1 ) * pageSize + " BID from BookInfo order by BID desc) order by BID desc";
			pstmt = conn.prepareStatement(sqlSelect);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("BID"));
				b.setTitle(rs.getString("BTitle"));
				b.setAuthor(rs.getString("BAuthor"));
				b.setPrice(rs.getDouble("BPrice"));
				b.setPublisher(rs.getString("BPublisher"));
				b.setPhoto(rs.getString("BPhoto"));
				b.getCategory().setId(rs.getInt("CID"));
				b.getCategory().setName(rs.getString("CName"));
				list.add(b);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}		
		return list;
	}
	
	public List<Book> getBooks(String cname, String btitle) {
		List<Book> list = new ArrayList<Book>();
		
		try {
			open();
			String sqlSelect = "select BID, BTitle, BAuthor, BPrice, BPublisher, BPhoto, CID, CName from BookInfo left join CategoryInfo on (BCategoryID = CID) where BTitle like ?";
			if(!cname.equals("全部")) {
				sqlSelect += " and CName = ?";
			}
			
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setString(1, "%" + btitle + "%");
			if(!cname.equals("全部")) {
				pstmt.setString(2, cname);
			}
			
			//System.out.println("【BookDao.getBooks】" + sqlSelect);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("BID"));
				b.setTitle(rs.getString("BTitle"));
				b.setAuthor(rs.getString("BAuthor"));
				b.setPrice(rs.getDouble("BPrice"));
				b.setPublisher(rs.getString("BPublisher"));
				b.setPhoto(rs.getString("BPhoto"));
				b.getCategory().setId(rs.getInt("CID"));
				b.getCategory().setName(rs.getString("CName"));
				list.add(b);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}		
		
		return list;
	}
	
	/* 根据条件分页查找图书信息 */
	public List<Book> getBooks(String cname, String btitle, int pageNO, int pageSize) {
List<Book> list = new ArrayList<Book>();
		
		try {
			open();
			String sqlCName = "";
			
			if(!cname.equals("全部")) {
				sqlCName = " and CName = ? ";
			}
			
			String sqlSelect = "select top " + pageSize + " BID, BTitle, BAuthor, BPrice, BPublisher, BPhoto, CID, CName from BookInfo left join CategoryInfo on (BCategoryID = CID) where BTitle like ? " + sqlCName + " and BID not in (select top " + (pageNO - 1) * pageSize + " BID from BookInfo left join CategoryInfo on (BCategoryID = CID) where BTitle like ? " + sqlCName + " order by BID desc) order by BID desc";
			
			pstmt = conn.prepareStatement(sqlSelect);
						
			if(!cname.equals("全部")) {
				pstmt.setString(1, "%" + btitle + "%");
				pstmt.setString(2, cname);
				pstmt.setString(3, "%" + btitle + "%");
				pstmt.setString(4, cname);
			} else {
				pstmt.setString(1, "%" + btitle + "%");
				pstmt.setString(2, "%" + btitle + "%");
			}
			
			System.out.println("【BookDao.getBooks条件分页】" + sqlSelect);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("BID"));
				b.setTitle(rs.getString("BTitle"));
				b.setAuthor(rs.getString("BAuthor"));
				b.setPrice(rs.getDouble("BPrice"));
				b.setPublisher(rs.getString("BPublisher"));
				b.setPhoto(rs.getString("BPhoto"));
				b.getCategory().setId(rs.getInt("CID"));
				b.getCategory().setName(rs.getString("CName"));
				list.add(b);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}		
		
		return list;
	}
	
	public Book getBookById(int id) {
		Book b = null;
		try {
			open();
			String sqlSelect = "select BID, BTitle, BAuthor, BPrice, BPublisher, BPhoto, CID, CName from BookInfo left join CategoryInfo on (BCategoryID = CID) where BID = ?";
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b = new Book();
				b.setId(rs.getInt("BID"));
				b.setTitle(rs.getString("BTitle"));
				b.setAuthor(rs.getString("BAuthor"));
				b.setPrice(rs.getDouble("BPrice"));
				b.setPublisher(rs.getString("BPublisher"));
				b.setPhoto(rs.getString("BPhoto"));
				b.getCategory().setId(rs.getInt("CID"));
				b.getCategory().setName(rs.getString("CName"));				
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
		return b;
	}
	
	/* 查询图书总数 */
	public int getCountTotal() {
		try {
			open();
			String sqlSelect = "select count(*) from BookInfo";			
			pstmt = conn.prepareStatement(sqlSelect);			
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
	
	/* 查询指定条件的图书总数 */
	public int getCountTotal(String cname, String btitle) {
		try {
			open();
			String sqlSelect = "select count(*) from BookInfo left join CategoryInfo on (BCategoryID = CID) where BTitle like ?";			
			if(!cname.equals("全部")) {
				sqlSelect += " and CName = ?";
			}			
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setString(1, "%" + btitle + "%");
			if(!cname.equals("全部")) {
				pstmt.setString(2, cname);
			}
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
	
	/* 查询指定分类ID的图书数量 */
	public int getCountByCid(int cid) {		
		try {
			open();
			String sqlSelect = "select count(*) from BookInfo where BCategoryID = ?";			
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setInt(1, cid);
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
	
	/* 向BookInfo表中插入一条图书信息记录（需要给一个Book类的实例，必须包含：title、author、price、publisher、category.id、photo） */
	public int add(Book b) {
		try {
			open();
			String sqlInsert = "  insert into ReplyInfo (ReID, ReContent, RePosterUID, ReReciverUID, ReDatetime, ReCoID) values (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sqlInsert);			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setDouble(3, b.getPrice());
			pstmt.setString(4, b.getPublisher());
			pstmt.setString(5, b.getPhoto());
			pstmt.setInt(6, b.getCategory().getId());
			return pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
	}
	
	public void delete(int id) {
		try {
			open();
			String sql = "delete from BookInfo where BID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
	}
	
	public void update(Book b) {
		try {
			open();
			String sql = "update BookInfo set BTitle = ?, BAuthor = ?, BPrice = ?, BPhoto = ?, BPublisher = ?, BCategoryID = ? where BID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setDouble(3, b.getPrice());
			pstmt.setString(4, b.getPhoto());
			pstmt.setString(5, b.getPublisher());
			pstmt.setInt(6, b.getCategory().getId());
			pstmt.setInt(7, b.getId());
			pstmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		BookDao bookDao = new BookDao();
		System.out.println(bookDao.getCountTotal("历史", "三国"));
	}


	
}
