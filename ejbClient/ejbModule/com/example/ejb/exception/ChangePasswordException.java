package com.example.ejb.exception;

import javax.ejb.EJBException;

public class ChangePasswordException extends EJBException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChangePasswordException(String message) {
		super(message);
	}
	
}
