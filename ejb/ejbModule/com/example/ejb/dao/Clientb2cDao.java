package com.example.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.Clientb2cDAORemote;
import com.example.ejb.dto.Clientb2cDTO;
import com.example.ejb.model.Clientb2b;
import com.example.ejb.model.Clientb2c; 

/**
 * Session Bean implementation class FirstStatelessEjb
 */

@Stateless
public class Clientb2cDao implements Clientb2cDAORemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public static Clientb2cDTO toDTO(Clientb2c data) {
		Clientb2cDTO client = new Clientb2cDTO();
		
		client.setUsername(data.getUsername());
		client.setPassword(data.getPassword());
		client.setEmail(data.getEmail());
		client.setAddress(data.getAddress());
		
		client.setCnp(data.getCnp());
		client.setDateRegister(data.getDateRegister());
		client.setNume(data.getNume());
		client.setPrenume(data.getPrenume()); 
		
		return client;
	}
	
	public static Clientb2c fromDTO(Clientb2cDTO data) {
		Clientb2c  client = new Clientb2c();
		
		client.setUsername(data.getUsername());
		client.setPassword(data.getPassword());
		client.setEmail(data.getEmail());
		client.setAddress(data.getAddress());
		
		client.setCnp(data.getCnp());
		client.setDateRegister(data.getDateRegister());
		client.setNume(data.getNume());
		client.setPrenume(data.getPrenume()); 
		
		return client;
	}
	
	@Override
	public void insert(Clientb2cDTO data) {   
		entityManager.persist(fromDTO(data)); 
	}

	@Override
	public void update(Clientb2cDTO data) {
		Clientb2c client = entityManager.find(Clientb2c.class, data.getId());
		
		client.setCnp(data.getCnp());
		client.setDateRegister(data.getDateRegister());
		client.setNume(data.getNume());
		client.setPrenume(data.getPrenume()); 
		
		entityManager.merge(client);
		entityManager.flush(); 
	}

	@Override
	public void delete(Clientb2cDTO data) {
		Clientb2c client = entityManager.find(Clientb2c.class, data.getId());  
		entityManager.remove(client);   
	}
	@Override
	public Clientb2cDTO findByCnp(String cnp) {
		Query query = entityManager.createQuery("SELECT c FROM Clientb2c c WHERE c.cnp = :cnp");
		query.setParameter("cnp", cnp);
		List<?> result = query.getResultList();
		
		if(result.size() == 0){ 
			return null;
		}
		
		Clientb2c found = (Clientb2c)result.get(0);
		Clientb2cDTO foundDTO = new Clientb2cDTO();
		foundDTO.setCnp(found.getCnp());
		foundDTO.setDateRegister(found.getDateRegister());
		foundDTO.setId(found.getId());
		foundDTO.setNume(found.getNume());
		foundDTO.setPrenume(found.getPrenume());
		
		return foundDTO; 
	}
	@Override
	public Clientb2cDTO getById(int id) {
		Clientb2c client = entityManager.find(Clientb2c.class, id);  
		return client == null? null : toDTO(client); 
	}

}
