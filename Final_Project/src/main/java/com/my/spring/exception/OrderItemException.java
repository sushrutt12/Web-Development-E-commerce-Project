package com.my.spring.exception;

public class OrderItemException extends Exception {

	public OrderItemException(String message)
	{
		super("OrderItemException-"+message);
	}
	
	public OrderItemException(String message, Throwable cause)
	{
		super("OrderItemException-"+message,cause);
	}
	
}
