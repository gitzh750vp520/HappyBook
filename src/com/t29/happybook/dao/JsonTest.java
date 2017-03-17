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
		Book book = new Book(1, "��������", "�޹���", 56.8, "�������������", "default.jpg", new Category(1001, "С˵"));
		Book book1 = new Book(2, "����", "����", 5.8, "��������", "default.jpg", new Category(1002, "С˵"));
		Book book2 = new Book(3, "����", "����", 6.8, "���������", "default.jpg", new Category(1003, "С˵"));
		books.add(book);
		books.add(book1);
		books.add(book2);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("book", book);
		
		System.out.println(jsonObject);
		
		
		JSONArray jsonArray = new JSONArray(books, false);
		System.out.println(jsonArray);

	}

}
