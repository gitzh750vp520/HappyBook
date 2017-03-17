package com.t29.happybook.service;

@SuppressWarnings("serial")
public class CartItem implements  java.io.Serializable {
	
	public CartItem() {
		
	}

	public CartItem(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.count = 1;
	}
	
	private int id;
	private String name;
	private double price;
	private int count;
	
	public double getTotal() {
		return this.price * this.count;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
