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
		UserDTO user_dto_result = null;

		Query query = entityManager
				.createQuery("SELECT c FROM User c WHERE c.username = :username AND c.password = :password");
		query.setParameter("username", loginAction.getUsername());
		query.setParameter("password", loginAction.getPassword());
		List<?> result = query.getResultList();

		if (result.size() != 0) {
			User user_result = (User) result.get(0); 
			
			user_dto_result = new UserDTO();
			
			user_dto_result.setAddress(user_result.getAddress());
			user_dto_result.setEmail(user_result.getEmail());
			user_dto_result.setId(user_result.getId());
			user_dto_result.setPassword(user_result.getPassword());
			user_dto_result.setUsername(user_result.getUsername()); 
			user_dto_result.setType(user_result.getDtype()); 
		} else
			throw new LoginException("invalid creditals!");

		return user_dto_result;
	}

	public boolean changePassword(ChangePasswordDTO changePass) throws ChangePasswordException {
		if(! changePass.getNewPassword().equals(changePass.getConfirmPassword())) {
			throw new ChangePasswordException("new password doesn't match");
		}
		
		LoginDTO test = new LoginDTO();
		test.setUsername(changePass.getUsername());
		test.setPassword(changePass.getOldPassword());
		
		try {
			login(test); // blind test

//			throw new ChangePasswordException("it's ok.");
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
