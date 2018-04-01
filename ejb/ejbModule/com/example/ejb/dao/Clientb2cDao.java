package com.example.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.Clientb2cDAORemote;
import com.example.ejb.dto.Clientb2cDTO;
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
	@Override
	public void insert(Clientb2cDTO data) { 
		Clientb2c client = new Clientb2c();
		
		client.setCnp(data.getCnp());
		client.setDateRegister(data.getDateRegister());
		client.setNume(data.getNume());
		client.setPrenume(data.getPrenume()); 
		
		entityManager.persist(client); 
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

}
