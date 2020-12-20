package com.plantkeeper.exception;

public class CustomerOrderNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerOrderNotFoundException(Long id){
		super("Could not find employee with id " + id);
	}
}
