package com.t29.happybook.service;

import java.util.List;

import com.t29.happybook.dao.BookDao;
import com.t29.happybook.entity.Book;

public class BookService {
	
	private BookDao bookDao = new BookDao();	

	public List<Book> getAllBooks() {	
		return bookDao.getAllBooks();		
	}	

	public List<Book> getBooks(int pageNO, int pageSize) {
		return bookDao.getBooks(pageNO, pageSize);
	}
	
	public List<Book> getBooks(String cname, String btitle, int pageNO, int pageSize) {
		return bookDao.getBooks(cname, btitle, pageNO, pageSize);
	}
	
	public int getCountTotal() {
		return bookDao.getCountTotal();
	}
	
	public int getCountTotal(String cname, String btitle) {
		return bookDao.getCountTotal(cname, btitle);
	}
	
	public List<Book> getBooks(String cname, String btitle) {		
		return bookDao.getBooks(cname, btitle);		
	}
	
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}
	
	public void save(Book b) {
		bookDao.add(b);
	}
	
	public void modify(Book b) {
		bookDao.update(b);
	}
	
	public void delete(int id) {
		bookDao.delete(id);
	}
	
	
	public static void main(String[] args) {
		
		Book b = new Book();
		b.setTitle("三重门");
		b.setAuthor("韩寒");
		b.setPrice(35.5);
		b.setPublisher("清华大学出版社");
		b.setPhoto("default.jpg");
		b.getCategory().setId(100);
		
		BookService bookService = new BookService();
		bookService.save(b);
		
		System.out.println("图书信息保存成功！");
	}
}
