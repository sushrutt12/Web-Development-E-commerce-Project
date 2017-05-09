package com.my.spring.exception;

public class RetailerException extends Exception {

	public RetailerException(String message)
	{
		super("RetailerException-"+message);
	}
	
	public RetailerException(String message, Throwable cause)
	{
		super("RetailerException-"+message,cause);
	}
	
}
