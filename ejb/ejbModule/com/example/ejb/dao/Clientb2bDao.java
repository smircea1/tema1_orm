package com.example.ejb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.Clientb2bDAORemote;
import com.example.ejb.dto.Clientb2bDTO;
import com.example.ejb.dto.Clientb2cDTO;
import com.example.ejb.model.Clientb2b;
import com.example.ejb.model.Clientb2c;
import com.example.ejb.model.StockClientb2b; 

@Stateless
public class Clientb2bDao implements Clientb2bDAORemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}

	public static Clientb2b fromDTO(Clientb2bDTO data) {
		Clientb2b client = new Clientb2b();  
		
		client.setUsername(data.getUsername());
		client.setPassword(data.getPassword());
		client.setEmail(data.getEmail());
		client.setAddress(data.getAddress());
		
		client.setCui(data.getCui());
		client.setNumeFirma(data.getNumeFirma()); 
		client.setSubscribed(data.getSubscribed());
		client.setType(data.getType());
		client.setVisibility(data.getVisibility()); 
		

//		List<StockClientb2b> stocks = new ArrayList<>();
//		client.setStockClientb2bs(data.getStockClientb2bs());
		return client;
	}
	public static Clientb2bDTO toDTO(Clientb2b data) {
		Clientb2bDTO client = new Clientb2bDTO();  
		
		client.setUsername(data.getUsername());
		client.setPassword(data.getPassword());
		client.setEmail(data.getEmail());
		client.setAddress(data.getAddress());
		
		client.setCui(data.getCui());
		client.setNumeFirma(data.getNumeFirma()); 
		client.setSubscribed(data.getSubscribed());
		client.setType(data.getType());
		client.setVisibility(data.getVisibility()); 
		

//		List<StockClientb2b> stocks = new ArrayList<>();
//		client.setStockClientb2bs(data.getStockClientb2bs());
		return client;
	}
	
	@Override
	public void insert(Clientb2bDTO data) { 
		entityManager.persist(fromDTO(data));  
	}

	@Override
	public void update(Clientb2bDTO data) {
		Clientb2b client = entityManager.find(Clientb2b.class, data.getId());
		
		client.setCui(data.getCui());
		client.setNumeFirma(data.getNumeFirma());
		client.setSubscribed(data.getSubscribed());
		client.setVisibility(data.getVisibility()); 
//		List<StockClientb2b> stocks = new ArrayList<>();
//		client.setStockClientb2bs(data.getStockClientb2bs());
		
		entityManager.merge(client);
		entityManager.flush();  
	}

	@Override
	public void delete(Clientb2bDTO data) {
		Clientb2b client = entityManager.find(Clientb2b.class, data.getId()); 
		
		entityManager.remove(client); 
	}

	@Override
	public Clientb2bDTO findByCui(String cui) {
		Query query = entityManager.createQuery("SELECT c FROM Clientb2c c WHERE c.cui = :cui");
		query.setParameter("cui", cui);
		List<?> result = query.getResultList();
		
		if(result.size() == 0){ 
			return null;
		}
		
		Clientb2b found = (Clientb2b)result.get(0);
		return toDTO(found); 
	}

	@Override
	public Clientb2bDTO getById(int id) {
		Clientb2b client = entityManager.find(Clientb2b.class, id);  
		return client == null? null : toDTO(client); 
	}
 

}
