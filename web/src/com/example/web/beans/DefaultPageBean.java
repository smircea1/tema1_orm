package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.example.ejb.dto.UserDTO;

@ManagedBean(name = "defaultPageBean")
@RequestScoped
public class DefaultPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {

	}

	public String logout() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("logged_user", null);
		try {
			context.getExternalContext().redirect("../login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			return "failure.";
		} 
		return "success.";
	}

	public String goToProfile() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO) session.getAttribute("logged_user");
		try {
			if (logged == null) { 
				context.getExternalContext().redirect("../login.xhtml"); 
			} else {
				switch (logged.getType()) {
				case "Clientb2b": {
					context.getExternalContext().redirect("profile_edit.xhtml");
				}
					break;
				case "Clientb2c": {
					context.getExternalContext().redirect("profile_edit.xhtml");
				}
					break;
				case "Supplier": {
					context.getExternalContext().redirect("profile_edit.xhtml");
				}
					break;
				default: {
					// IGNORE
				}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "failure.";
		}
		return "success.";
	}
}