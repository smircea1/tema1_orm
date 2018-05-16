package com.example.ejb.actions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.dto.Clientb2cDTO;
import com.example.ejb.dto.UserDTO;
import com.example.ejb.exception.ChangePasswordException;
import com.example.ejb.exception.LoginException;
import com.example.ejb.model.User;
import com.example.ejb.webDTO.ChangePasswordDTO;
import com.example.ejb.webDTO.LoginDTO;
import com.example.ejb.webRemote.WebActionsRemote;

@Stateless
public class WebActions implements WebActionsRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public UserDTO login(LoginDTO loginAction) throws LoginException {
		UserDTO dtoResult = null;

		Query query = entityManager
				.createQuery("SELECT c FROM User c WHERE c.username = :username AND c.password = :password");
		query.setParameter("username", loginAction.getUsername());
		query.setParameter("password", loginAction.getPassword());
		List<?> result = query.getResultList();

		if (result.size() != 0) {
			User userResult = (User) result.get(0); 
			
			dtoResult = new UserDTO();
			
			dtoResult.setAddress(userResult.getAddress());
			dtoResult.setEmail(userResult.getEmail());
			dtoResult.setId(userResult.getId());
			dtoResult.setPassword(userResult.getPassword());
			dtoResult.setUsername(userResult.getUsername()); 
			dtoResult.setType(userResult.getDtype()); 
		} else
			throw new LoginException("invalid creditals!");

		return dtoResult;
	}

	public boolean changePassword(ChangePasswordDTO changePass) throws ChangePasswordException {
		if(! changePass.getNewPassword().equals(changePass.getConfirmPassword())) {
			throw new ChangePasswordException("new password doesn't match");
		}
		
		LoginDTO test = new LoginDTO();
		test.setUsername(changePass.getUsername());
		test.setPassword(changePass.getOldPassword());
		
		try {
			login(test); 
			Query query = entityManager.createQuery("UPDATE User SET password = :password WHERE username = :username");
			query.setParameter("username", changePass.getUsername());
			query.setParameter("password", changePass.getNewPassword());
			
			int affected = query.executeUpdate();
			return affected > 0;
		} catch (LoginException e) {
			throw new ChangePasswordException("new password doesn't match");
		}   
	}
}
