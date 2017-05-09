package com.my.spring.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.RetailerException;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Retailer;


public class RetailerDAO extends DAO {

	public RetailerDAO() {
	}

	public ArrayList<Retailer> getRetailerList() throws RetailerException {
		ArrayList<Retailer> retailerList=null;
		try{
		begin();
		Query q = getSession().createQuery("from Retailer");
		retailerList = (ArrayList <Retailer>)q.list();
		commit();
		close();
		return retailerList;
		} catch (HibernateException e) {
			throw new RetailerException("Exception while creating retailer: " + e.getMessage());
		}
		
		
	}
	
	public Retailer getRetailer(String username, String password) throws RetailerException {
		try {
			begin();
			Query q = getSession().createQuery("from Retailer where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Retailer retailer = (Retailer) q.uniqueResult();
			commit();
			close();
			return retailer;
		} catch (HibernateException e) {
			rollback();
			throw new RetailerException("Could not get retailer " + username, e);
		}
	}
	
	public Retailer getRetailer(int personId) throws RetailerException {
		try {
			begin();
			Query q = getSession().createQuery("from Retailer where personID= :personID");
			q.setInteger("personID", personId);		
			Retailer retailer = (Retailer) q.uniqueResult();
			commit();
			return retailer;
		} catch (HibernateException e) {
			rollback();
			throw new RetailerException("Could not get retailer " + personId, e);
		}
	}

	
	public long getRetailerId(String userName) throws RetailerException {
		try {
			begin();
			Query q = getSession().createQuery("select personID from Retailer where username= :userName");
			q.setString("userName", userName);		
			long id = (Long)(q.uniqueResult());
			commit();
			return id;
		} catch (HibernateException e) {
			rollback();
			throw new RetailerException("Could not get Retailer " + userName, e);
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
	
	public Retailer register(Retailer r)
			throws RetailerException {
		try {
			begin();
//			System.out.println("inside DAO");

			Email email = new Email(r.getEmail().getEmailAddress());
			Retailer retailer= new Retailer();
			retailer.setUsername(r.getUsername());
			retailer.setPassword(r.getPassword());
			
			retailer.setStatus(r.getStatus());
			retailer.setFirstName(r.getFirstName());
			retailer.setLastName(r.getLastName());
//			retailer.setRole(r.getRole());
			retailer.setEmail(email);
			email.setPerson(retailer);
			getSession().save(retailer);
			commit();
			return retailer;

		} catch (HibernateException e) {
			rollback();
			throw new RetailerException("Exception while creating retailer: " + e.getMessage());
		}
	}

	public void delete(Retailer retailer) throws RetailerException {
		try {
			begin();
			getSession().delete(retailer);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new RetailerException("Could not delete retailer " + retailer.getUsername(), e);
		}
	}
}