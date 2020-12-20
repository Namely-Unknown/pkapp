package com.plantkeeper.exception;

public class OrderItemNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderItemNotFoundException(Long id){
		super("Could not find employee with id " + id);
	}
}
