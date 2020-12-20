package com.plantkeeper.exception;

public class ContainerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContainerNotFoundException(Long id){
		super("Could not find container with id " + id);
	}
}
