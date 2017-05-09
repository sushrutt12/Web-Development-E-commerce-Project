package com.my.spring.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.AdminException;
import com.my.spring.exception.CustomerException;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Admin;
import com.my.spring.pojo.Customer;

public class AdminDAO extends DAO {

	public AdminDAO() {
	}

	public ArrayList<Admin> getAdminList() throws AdminException {
		ArrayList<Admin> adminList=null;
		try{
		begin();
		Query q = getSession().createQuery("from Customer");
		adminList = (ArrayList <Admin>)q.list();
		commit();
		close();
		return adminList;
		} catch (HibernateException e) {
			throw new AdminException("Exception while creating Admin: " + e.getMessage());
		}
		
		
	}
	
	public Customer getAdmin(String username, String password) throws AdminException {
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
			throw new AdminException("Could not get user " + username, e);
		}
	}
	
	public Customer getAdmin(int userId) throws AdminException {
		try {
			begin();
			Query q = getSession().createQuery("from Admin where personID= :personID");
			q.setInteger("personID", userId);		
			Customer user = (Customer) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new AdminException("Could not get Admin " + userId, e);
		}
	}

	
	public long getAdminId(String userName) throws AdminException {
		try {
			begin();
			Query q = getSession().createQuery("select personID from Person where username= :userName");
			q.setString("userName", userName);		
			long id = (Long)(q.uniqueResult());
			commit();
			return id;
		} catch (HibernateException e) {
			rollback();
			throw new AdminException("Could not get Admin " + userName, e);
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
	
	public Admin register(Admin u)
			throws AdminException {
		try {
			begin();

			Email email = new Email(u.getEmail().getEmailAddress());
			Admin user = new Admin();
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
			throw new AdminException("Exception while creating Admin: " + e.getMessage());
		}
	}

	public void delete(Admin c) throws AdminException {
		try {
			begin();
			getSession().delete(c);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdminException("Could not delete Admin " + c.getUsername(), e);
		}
	}
}