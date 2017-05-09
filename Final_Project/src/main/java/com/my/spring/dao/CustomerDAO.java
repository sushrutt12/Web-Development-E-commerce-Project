package com.my.spring.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.my.spring.exception.CustomerException;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Customer;

public class CustomerDAO extends DAO {

	public CustomerDAO() {
	}

	public ArrayList<Customer> getCustomerList() throws CustomerException {
		ArrayList<Customer> customerList=null;
		try{
		begin();
		Query q = getSession().createQuery("from Customer");
		customerList = (ArrayList <Customer>)q.list();
		commit();
		close();
		return customerList;
		} catch (HibernateException e) {
			throw new CustomerException("Exception while creating user: " + e.getMessage());
		}
		
		
	}
	
	
	public Customer getCustomer(String username, String password) throws CustomerException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Customer customer = (Customer) q.uniqueResult();
			commit();
			close();
			return customer;
		} catch (HibernateException e) {
			rollback();
			throw new CustomerException("Could not get Customer" + username, e);
		}
	}
	
	public Customer getCustomer(int userId) throws CustomerException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer where personID= :personID");
			q.setInteger("personID", userId);		
			Customer user = (Customer) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new CustomerException("Could not get user " + userId, e);
		}
	}

	
	public long getCustomerId(String userName) throws CustomerException {
		try {
			begin();
			Query q = getSession().createQuery("select personID from Person where username= :userName");
			q.setString("userName", userName);		
			long id = (Long)(q.uniqueResult());
			commit();
			return id;
		} catch (HibernateException e) {
			rollback();
			throw new CustomerException("Could not get customer " + userName, e);
		}
	}

	
	
//	public void updateRole(int userId,String role) throws CustomerException {
//		try {
//			begin();
//			Query q = getSession().createQuery("from User where personID= :personID");
//			q.setInteger("personID", userId);		
//			Customer user = (Customer) q.uniqueResult();
//			user.setRole(role);
//			getSession().save(user);
//			commit();
//			
//		} catch (HibernateException e) {
//			rollback();
//			throw new CustomerException("Could not get user " + userId, e);
//		}
//	}
	
	public Customer register(Customer u)
			throws CustomerException {
		try {
			begin();
//			System.out.println("inside DAO");

			Email email = new Email(u.getEmail().getEmailAddress());
			Customer user = new Customer();
			user.setUsername(u.getUsername());
			user.setPassword(u.getPassword());
			user.setStatus(u.getStatus());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
//			user.setRole(u.getRole());
			user.setEmail(email);
			email.setPerson(user);
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new CustomerException("Exception while creating Customer: " + e.getMessage());
		}
	}

	public void delete(Customer c) throws CustomerException {
		try {
			begin();
			getSession().delete(c);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CustomerException("Could not delete customer " + c.getUsername(), e);
		}
	}
}