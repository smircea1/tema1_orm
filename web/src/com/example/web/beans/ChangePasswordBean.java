package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.example.ejb.dto.UserDTO;
import com.example.ejb.exception.ChangePasswordException;
import com.example.ejb.webDTO.ChangePasswordDTO;
import com.example.ejb.webDTO.LoginDTO;
import com.example.ejb.webRemote.WebActionsRemote;

@ManagedBean(name = "changePasswordBean")
@RequestScoped
public class ChangePasswordBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ChangePasswordDTO changeDTO;
 
	@EJB
	private WebActionsRemote actions;
	
	public ChangePasswordBean() {
		changeDTO = new ChangePasswordDTO();
	}
	
	@PostConstruct
	public void init() {
		changeDTO = new ChangePasswordDTO();
	}

	
	public ChangePasswordDTO getChangeDTO() {
		return changeDTO;
	}

	public void setChangeDTO(ChangePasswordDTO changeDTO) {
		this.changeDTO = changeDTO;
	}

	public String actionChange() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("logged_user");
		
		changeDTO.setUsername(logged.getUsername());
		
		try {
			actions.changePassword(changeDTO);
			context.addMessage("changeForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "success", null )); 
//			context.getExternalContext().redirect("login.xhtml");
		}catch(ChangePasswordException e) {
			context.addMessage("changeForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null )); 
		}
		return "success.";
	}
}