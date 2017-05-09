package com.my.spring.pojo;

import java.util.ArrayList;

public class Cart {
private ArrayList<CartItems> cartItems=new ArrayList<CartItems>();
private double totalPrice;


public Cart() {
	
}




public double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}

public ArrayList<CartItems> getCartItems() {
	return cartItems;
}

public void setCartItems(ArrayList<CartItems> cartItems) {
	this.cartItems = cartItems;
}

}
