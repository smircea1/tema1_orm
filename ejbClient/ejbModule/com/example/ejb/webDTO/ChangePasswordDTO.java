package com.example.ejb.webDTO;

import java.io.Serializable;

public class ChangePasswordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public String username;
	public String oldPassword;
	public String newPassword;
	public String confirmPassword;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
 
	 
}
