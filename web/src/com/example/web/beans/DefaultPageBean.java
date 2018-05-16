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
		session.setAttribute("loggedUser", null);
		
		return goLogin(); 
	}

	public String goLogin() {
		final FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("../login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}

	public String goHome(UserDTO loggedUser) {
		final FacesContext context = FacesContext.getCurrentInstance();
		try {
			switch (loggedUser.getType()) {
			case "Clientb2b": {
				context.getExternalContext().redirect("client_b2b/home_client_b2b.xhtml");
			}
				break;
			case "Clientb2c": {
				context.getExternalContext().redirect("client_b2c/home_client_b2c.xhtml");
			}
				break;
			case "Supplier": {
				context.getExternalContext().redirect("supplier_change_pass.xhtml");
			}
				break;
			default: {

			}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}

	public String goToProfile() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO) session.getAttribute("loggedUser");
		try {
			if (logged == null) {
				return goLogin();
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