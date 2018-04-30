package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.glassfish.grizzly.http2.Http2Session;

import com.example.ejb.dto.UserDTO;
import com.example.ejb.exception.LoginException;
import com.example.ejb.webDTO.LoginDTO;
import com.example.ejb.webRemote.WebActionsRemote;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginDTO loginDTO;

	@EJB
	private WebActionsRemote actions;

	public LoginBean() {
		loginDTO = new LoginDTO();
	}

	@PostConstruct
	public void init() {
		loginDTO = new LoginDTO();
	}

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public String actionLogin() {
		final FacesContext context = FacesContext.getCurrentInstance();

		try {
			UserDTO result = actions.login(loginDTO);
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("logged_user", result);
			switch (result.getType()) {
			case "Clientb2b": {
				context.getExternalContext().redirect("default_view_client_b2b.xhtml");
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
				// IGNORE
			}
			}
		} catch (LoginException | IOException e1) { 
			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), null ));
			return "failed";
		} 

		// do select stuff etc etc.

		// REDIRECT
		// NavigationHandler navHandler =
		// FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		// navHandler.handleNavigation(FacesContext.getCurrentInstance(), null,
		// "web/faces/changepass.xhtml");

		// OR.
//		ExternalContext externalContext = context.getExternalContext();
//		try {
//			externalContext.redirect("web/faces/changepass.xhtml");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "success.";
	}
}
