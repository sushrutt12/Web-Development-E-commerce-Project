package com.my.spring.exception;

public class RatingException extends Exception {

	public RatingException(String message)
	{
		super("RatingException-"+message);
	}
	
	public RatingException(String message, Throwable cause)
	{
		super("RatingException-"+message,cause);
	}
	
}
