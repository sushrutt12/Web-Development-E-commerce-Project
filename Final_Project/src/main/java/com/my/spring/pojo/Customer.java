package com.my.spring.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="customer_table")
@PrimaryKeyJoinColumn(name="personID")
public class Customer extends Person {
	@OneToMany(mappedBy = "customer")
	private Set<Order> orderList=new HashSet<Order>();
	
    @Transient
	private ArrayList<Card> cardList;
	

	public Customer() {
		cardList = new ArrayList<Card>();
		
		this.setRole("Customer");
	}


	

	public Set<Order> getOrderList() {
		return orderList;
	}




	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}




	public ArrayList<Card> getCardList() {
		return cardList;
	}


	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}

	
	
	
}