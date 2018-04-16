package com.example.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.example.ejb.webDTO.ChangePasswordDTO;
import com.example.ejb.webDTO.LoginDTO;

@ManagedBean(name = "changePasswordBean")
@RequestScoped
public class ChangePasswordBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ChangePasswordDTO changeDTO;

	public String actionChange() {
		final FacesContext context = FacesContext.getCurrentInstance();
		
		
		
		return "success.";
	}
}