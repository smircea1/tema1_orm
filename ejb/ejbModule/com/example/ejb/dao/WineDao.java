package com.example.ejb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.WineDAORemote;
import com.example.ejb.dto.Clientb2bDTO;
import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.SupplierDTO;
import com.example.ejb.dto.WineDTO;
import com.example.ejb.model.StockSupplier;
import com.example.ejb.model.User;
import com.example.ejb.model.Wine;


@Stateless
public class WineDao implements WineDAORemote{


	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public static WineDTO toDTO(Wine data) {
		WineDTO wine = new WineDTO();
		
		wine.setDescription(data.getDescription());
		wine.setId(data.getId());
		wine.setTip(data.getTip());
		wine.setName(data.getName());
		wine.setSoi(data.getSoi());
		wine.setYear(data.getYear());
 
		return wine;
	}
	public static Wine fromDTO(WineDTO data) {
		Wine wine = new Wine();
		
		wine.setDescription(data.getDescription());
		wine.setId(data.getId());
		wine.setTip(data.getTip());
		wine.setName(data.getName());
		wine.setSoi(data.getSoi());
		wine.setYear(data.getYear());
		 
		return wine;
	}
	
	@Override
	public void insert(WineDTO data) {
		entityManager.persist(fromDTO(data)); 
	}

	@Override
	public void update(WineDTO data) {
		Wine updated = entityManager.find(Wine.class, data.getId());
		if(updated == null)
			return;

		entityManager.merge(fromDTO(data));
		entityManager.flush(); 
	}

	@Override
	public void delete(WineDTO data) {
		Wine wine = entityManager.find(Wine.class, data.getId());   
		entityManager.remove(wine);   
	}

	@Override
	public WineDTO getById(int id) {
		Query query = entityManager.createQuery("SELECT c FROM Wine c WHERE c.id = :id");
		query.setParameter("id", id); 
		List<WineDTO> queryResult = toDTOList(query.getResultList()); 
		return queryResult.size() == 0? null : queryResult.get(0);
	} 
	
	private List<WineDTO> toDTOList(List<?> objList){
		List<WineDTO> result = new ArrayList<>();
		for(Object obj : objList) {
			result.add( toDTO((Wine) obj));
		}
		return result;
	}

}
