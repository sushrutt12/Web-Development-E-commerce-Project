package com.my.spring.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.my.spring.pojo.Cart;
import com.my.spring.pojo.CartItems;

public class CartDAO extends DAO {
	public CartDAO() {

	}

	public Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}
		return cart;

	}

	public void addCartItems(HttpServletRequest request, CartItems ci) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		System.out.println("Cart>>" + cart);
		
		if (cart != null) {
			System.out.println("In not null" + cart);
			System.out.println("Cart size>>" + cart.getCartItems().size());
			boolean flag=true;
			for (CartItems cai : cart.getCartItems()) {
				if (ci.getProduct().getId() == cai.getProduct().getId()) {
					cai.setQuantity(ci.getQuantity());
					flag=false;
				}

			}
			if(flag)
			{
				cart.getCartItems().add(ci);
				
			}
		}
		else {
			
			cart = new Cart();
			cart.getCartItems().add(ci);
		}
		session.setAttribute("cart", cart);
	}

	public void deleteCartItems(HttpServletRequest request, long ciid) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		for (CartItems cis : cart.getCartItems()) {
			if (cis.getId() == ciid) {
				cart.getCartItems().remove(cis);
				session.setAttribute("cart", cart);
				break;
			}
		}

	}

}
