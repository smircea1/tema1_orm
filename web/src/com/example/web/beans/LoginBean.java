package com.example.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.example.ejb.webDTO.LoginDTO;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginDTO loginDTO;

	public String actionLogin() {
		final FacesContext context = FacesContext.getCurrentInstance();
		
		
		
		return "success.";
	}
}
