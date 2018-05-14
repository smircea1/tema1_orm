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

import com.example.ejb.daoRemote.Clientb2cDAORemote;
import com.example.ejb.daoRemote.UserDAORemote;
import com.example.ejb.dto.Clientb2cDTO;
import com.example.ejb.dto.UserDTO;
import com.example.ejb.exception.LoginException;
import com.example.ejb.webDTO.LoginDTO;
import com.example.ejb.webRemote.WebActionsRemote;

@ManagedBean(name = "updateB2CUserBean")
@RequestScoped
public class UpdateB2CUserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Clientb2cDTO temporaryUser = null;

	@EJB
	private Clientb2cDAORemote client_dao;

	public UpdateB2CUserBean() { 
//		fetchTemporaryUser();
	}

	@PostConstruct
	public void init() { 
		fetchTemporaryUser();
	} 

	private void fetchTemporaryUser() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO) session.getAttribute("logged_user");
		if(logged == null)
			return;
		 
		temporaryUser = client_dao.getById(logged.getId());
	}

	public Clientb2cDTO getTemporaryUser() {
		return temporaryUser;
	}

	public String actionUpdate() {
		if(temporaryUser == null)
			return "Failure";
		
//		final FacesContext context = FacesContext.getCurrentInstance();
		client_dao.update(temporaryUser);
//		try {
//			switch (temporaryUser.getType()) {
//			case "Clientb2b": {
//				context.getExternalContext().redirect("client_b2b/home_client_b2b.xhtml");
//			}
//				break;
//			case "Clientb2c": {
//				context.getExternalContext().redirect("client_b2c/home_client_b2c.xhtml");
//			}
//				break;
//			case "Supplier": {
//				context.getExternalContext().redirect("supplier/home_supplier.xhtml");
//			}
//				break;
//			default: {
//				// IGNORE
//			}
//			}
//		} catch (LoginException | IOException e1) {
//			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), null));
//			return "failed";
//		}
		return "success.";
	}
}
