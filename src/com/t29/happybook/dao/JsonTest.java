package com.t29.happybook.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.t29.happybook.entity.Book;
import com.t29.happybook.entity.Category;



public class JsonTest {

	public static void main(String[] args) throws JSONException {
		List<Book> books = new ArrayList<Book>();
		Book book = new Book(1, "三国演义", "罗贯中", 56.8, "人民教育出版社", "default.jpg", new Category(1001, "小说"));
		Book book1 = new Book(2, "西游记", "吴承恩", 45.8, "育出版社", "default.jpg", new Category(1002, "小说"));
		Book book2 = new Book(3, "红楼梦", "曹雪芹", 26.8, "人民教育社", "default.jpg", new Category(1003, "小说"));
		Book book3 = new Book(4, "水浒传", "施耐庵", 36.8, "机械工业出版社", "default.jpg", new Category(1004, "小说"));
		books.add(book);
		books.add(book1);
		books.add(book2);
		books.add(book3);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("book", book);
		jsonObject.put("books", books);
		
		System.out.println(jsonObject);
		
		
		JSONArray jsonArray = new JSONArray(books, false);
		System.out.println(jsonArray);

	}

}
