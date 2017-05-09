package com.my.spring.pojo;

public class CartItems {
	
	private long id; 
	private static long count =1; 
	private int quantity;
	
	private Product product;
	
	
	
	public CartItems(int quantity, Product product) {
		this.id=count++;
		this.quantity = quantity;
		
		this.product = product;
	}
	public CartItems() 
	{
		this.id=count++;
		
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
