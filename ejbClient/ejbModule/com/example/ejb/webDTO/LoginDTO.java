package com.example.ejb.webDTO;

import java.io.Serializable;
 
import javax.ejb.Remote;

//import com.example.ejb.dto.OrderDTO;
//
//@Remote
public class LoginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String username;
	public String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
 
	
}
