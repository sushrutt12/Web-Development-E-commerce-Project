package com.my.spring.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.my.spring.exception.CustomerException;
import com.my.spring.exception.PersonException;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Person;
import com.my.spring.pojo.Customer;

public class EmailDAO extends DAO {

	public EmailDAO() {
	}

	public Email getEmail(String email) throws Exception {
		
		try{
		begin();
		Query q = getSession().createQuery("from Email where emailAddress= :email");
		q.setString("email", email);
		Email e=(Email)q.uniqueResult();
		
		close();
		return e;
		} catch (HibernateException hb) {
			throw new Exception("Exception while getting email: " + hb.getMessage());
		}
		
	}	
	
}