package com.example.ejb.exception;

import javax.ejb.EJBException;
 
public class LoginException extends EJBException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginException(String message) {
		super(message);
	}

}
