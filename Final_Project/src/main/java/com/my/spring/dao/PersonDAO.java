package com.my.spring.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.my.spring.exception.CustomerException;
import com.my.spring.exception.PersonException;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Person;
import com.my.spring.pojo.Customer;

public class PersonDAO extends DAO {

	public PersonDAO() {
	}

	public ArrayList<Person> getPersonList() throws PersonException {
		ArrayList<Person> list=null;
		try{
		begin();
		Query q = getSession().createQuery("from Person");
		list = (ArrayList <Person>)q.list();
		commit();
		close();
		return list;
		} catch (HibernateException e) {
			throw new PersonException("Exception while creating user: " + e.getMessage());
		}
		
		
	}
	
	public Person getPerson(String username, String password) throws PersonException {
		try {
			begin();
			Query q = getSession().createQuery("from Person where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Person person = (Person) q.uniqueResult();
			commit();
			close();
			return person;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person" + username, e);
		}
	}
	
	public Person getPerson(long userId) throws PersonException {
		try {
			begin();
			Query q = getSession().createQuery("from Person where personID= :personID");
			q.setLong("personID", userId);		
			Person user = (Person) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get Person " + userId, e);
		}
	}

	
	public long getPersonId(String userName) throws PersonException {
		try {
			begin();
			Query q = getSession().createQuery("select personID from Person where username= :userName");
			q.setString("userName", userName);		
			long id = (Long)(q.uniqueResult());
			commit();
			return id;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person " + userName, e);
		}
	}
	
	public Person getPerson(String userName) throws PersonException {
		try {
			begin();
			Query q = getSession().createQuery("from Person where username= :userName");
			q.setString("userName", userName);		
			Person p=(Person)q.uniqueResult();
			System.out.println(p);
			commit();
			return p;
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get person " + userName, e);
		}
	}
	
	

	
	
	public void updateStatus(int userId,String status) throws PersonException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
			q.setInteger("personID", userId);		
			Customer user = (Customer) q.uniqueResult();
			user.setStatus(status);
			getSession().save(user);
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new PersonException("Could not get status " + userId, e);
		}
	}
	
//	public Customer register(Customer u)
//			throws CustomerException {
//		try {
//			begin();
////			System.out.println("inside DAO");
//
//			Email email = new Email(u.getEmail().getEmailAddress());
//			Customer user = new Customer();
//			user.setUsername(u.getUsername());
//			user.setPassword(u.getPassword());
//			user.setStatus(u.getStatus());
//			user.setFirstName(u.getFirstName());
//			user.setLastName(u.getLastName());
////			user.setRole(u.getRole());
//			user.setEmail(email);
//			email.setPerson(user);
//			getSession().save(user);
//			commit();
//			return user;
//
//		} catch (HibernateException e) {
//			rollback();
//			throw new CustomerException("Exception while creating Customer: " + e.getMessage());
//		}
//	}

//	public void delete(Customer c) throws CustomerException {
//		try {
//			begin();
//			getSession().delete(c);
//			commit();
//		} catch (HibernateException e) {
//			rollback();
//			throw new CustomerException("Could not delete customer " + c.getUsername(), e);
//		}
//	}
}