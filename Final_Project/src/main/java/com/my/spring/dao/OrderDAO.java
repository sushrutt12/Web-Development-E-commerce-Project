package com.my.spring.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.my.spring.exception.CustomerException;
import com.my.spring.exception.OrderException;
import com.my.spring.exception.OrderItemException;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.CartItems;
import com.my.spring.pojo.Customer;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.OrderItem;
import com.my.spring.pojo.Product;

public class OrderDAO extends DAO {
	
	
	public OrderDAO() {

	}

	public void createOrder(Order o) throws OrderException {
		
		try {
			begin();
			
			getSession().save(o);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OrderException("Exception while creating Order: " + e.getMessage());
		}
		
	}
	public List<Order> getOrderList(long customerID)
	{
		begin();
		Query q = getSession().createQuery("from Order where customerID= :customerID");
		q.setLong("customerID", customerID);
		List<Order> orderList= q.list();
		return orderList;
		
	}

}
