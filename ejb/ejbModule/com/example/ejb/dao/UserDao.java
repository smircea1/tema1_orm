package com.example.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.UserDAORemote;
import com.example.ejb.dto.UserDTO;
import com.example.ejb.model.User;
/**
 * Session Bean implementation class FirstStatelessEjb
 */
@Stateless
public class UserDao implements UserDAORemote {
 
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insert(UserDTO data) {
		User user = new User();
		
		user.setAddress(data.getAddress());
		user.setEmail(data.getEmail());
		user.setPassword(data.getPassword());
		user.setUsername(data.getUsername());
		user.setType(data.getType());
		
		entityManager.persist(user); 
	}

	@Override
	public void update(UserDTO data) {
		User user = entityManager.find(User.class, data.getId());
		 
		user.setAddress(data.getAddress());
		user.setEmail(data.getEmail());
		user.setPassword(data.getPassword());
		user.setUsername(data.getUsername());
		user.setType(data.getType());
		
		entityManager.merge(user);
		entityManager.flush(); 
	}

	@Override
	public void delete(UserDTO data) {
		User user = entityManager.find(User.class, data.getId());  
		
		entityManager.remove(user);   
	} 

	@Override
	public UserDTO findByUsername(String username) {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
		query.setParameter("username", username);
		List<?> result = query.getResultList();
		if(result.size() == 0){ 
			return null;
		}
		
		User found = (User)result.get(0);
		
		UserDTO foundDTO = new UserDTO();
		foundDTO.setId(found.getId());
		foundDTO.setAddress(found.getAddress());
		foundDTO.setEmail(found.getEmail());
		foundDTO.setPassword(found.getPassword());
		foundDTO.setUsername(found.getUsername());
		foundDTO.setType(found.getType());
		
		return foundDTO;
	}

}
