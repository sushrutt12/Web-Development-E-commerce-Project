package com.my.spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.my.spring.dao.EmailDAO;
import com.my.spring.dao.PersonDAO;
import com.my.spring.exception.PersonException;
import com.my.spring.pojo.Customer;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Person;

public class CustomerValidator implements Validator {


	
	

	
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Customer.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		
		
		        
		        
		Customer customer = (Customer) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.customer", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.customer", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.customer", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
		
		
		Pattern p = Pattern.compile("[\\w|\\-\\.|_|:|\\s]+");
		Matcher matcher = p.matcher(customer.getFirstName());
		if(!matcher.matches())
            errors.rejectValue("firstName", "error.invalid.firstName", "FirstName cannot contain special characters");
		matcher = p.matcher(customer.getLastName());
		if(!matcher.matches())
            errors.rejectValue("lastName", "error.invalid.lastName", "LastName cannot contain special characters");
		matcher = p.matcher(customer.getUsername());
		if(!matcher.matches())
            errors.rejectValue("username", "error.invalid.username", "UserName cannot contain special characters");
		 matcher = p.matcher(customer.getPassword());
		if(!matcher.matches())
            errors.rejectValue("password", "error.invalid.password", "Password cannot contain special characters");
		
		System.out.println(customer.getUsername());
		
		try {
			PersonDAO personDao= new PersonDAO();
			Person person = personDao.getPerson(customer.getUsername());
			if(!(person ==null))
				errors.rejectValue("username", "error.invalid.username", "Username already Exists");
			
		} catch (PersonException e) {
			System.err.println("Exception in Person Validator");
		}
		
		
		try {
			EmailDAO personDao= new EmailDAO();
			Email email= personDao.getEmail(customer.getEmail().getEmailAddress());
			if(!(email ==null))
				errors.rejectValue("email.emailAddress", "error.invalid.email.emailAddress", "Email already Exists");
			
		} catch (Exception e) {
			System.err.println("Exception in Person Validator");
		}
		
		
	}
}
