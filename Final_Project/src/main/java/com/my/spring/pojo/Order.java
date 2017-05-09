package com.my.spring.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order_table")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderID", unique=true, nullable=false)
	private long orderID;
	@OneToMany
    private Set<OrderItem> orderItemList=new HashSet<OrderItem>();
//	
	@ManyToOne
	@JoinColumn(name="customerID")
	private Customer customer;
	
	public Order() {
	
	}
	
//	public Order(Customer customer, Cart cart) {
//		this.customer=customer;
//		this.cart = cart;
//	}
	public long getOrderId() {
		return orderID;
	}
	public void setOrderId(long orderId) {
		this.orderID = orderId;
	}


	public Set<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(Set<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
