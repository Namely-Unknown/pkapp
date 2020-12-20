package com.plantkeeper.exception;

public class PlantNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlantNotFoundException(Long id){
		super("Could not find plant with id " + id);
	}

}
