package com.t29.happybook.entity;

public class Book {

	public Book() {
	}
	public Book(int id, String title, String author, double price,
			String publisher, String photo, Category category) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.photo = photo;
		this.category = category;
	}
	private int id;	
	private String title;
	private String author;
	private double price;
	private String publisher;
	private String photo;
	private Category category = new Category();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
