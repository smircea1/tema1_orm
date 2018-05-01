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


//		for(StockSupplier suppl : data.getStockSuppliers()) {
//			wine.addStockSupplier(StockSupplierDao.toDTO(suppl));
//		}
		
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
		
//		for(StockSupplierDTO suppl : data.getStockSuppliers()) {
//			wine.addStockSupplier(StockSupplierDao.fromDTO(suppl));
//		}
		
		
//		wine.setStockSuppliers(data.getStockSuppliers());
		
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
//		updated.setDescription(data.getDescription());
//		updated.setName(data.getName());
//		updated.setTip(data.getTip());
//		updated.setYear(data.getYear());
//		updated.setSoi(data.getSoi());

//		updated.setStockSuppliers(data.getStockSuppliers()); 
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
		List<WineDTO> query_result = toDTOList(query.getResultList()); 
		return query_result.size() == 0? null : query_result.get(0);
	} 
	
	private List<WineDTO> toDTOList(List<?> obj_list){
		List<WineDTO> result = new ArrayList<>();
		for(Object obj : obj_list) {
			result.add( toDTO((Wine) obj));
		}
		return result;
	}

}
