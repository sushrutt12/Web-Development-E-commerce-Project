package com.my.spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.my.spring.pojo.Product;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Product.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Product newProduct = (Product) obj;
		Pattern p = Pattern.compile("[\\w|\\-\\.|_|:|\\s]+");
		Matcher matcher = p.matcher(newProduct.getTitle());
		if(!matcher.matches())
            errors.rejectValue("title", "error.invalid.title", "Title cannot contain special characters");
		matcher = p.matcher(newProduct.getDescription());
		if(!matcher.matches())
            errors.rejectValue("description", "error.invalid.description", "Description cannot contain special characters");
		
		
	}
	
}
