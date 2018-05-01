package com.example.ejb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.ejb.daoRemote.SupplierDAORemote;
import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.SupplierDTO;
import com.example.ejb.model.Clientb2b;
import com.example.ejb.model.StockSupplier;
import com.example.ejb.model.Supplier;

@Stateless
public class SupplierDao implements SupplierDAORemote{

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public static SupplierDTO toDTO(Supplier data) {
		SupplierDTO supp = new SupplierDTO();
		
		supp.setUsername(data.getUsername());
		supp.setPassword(data.getPassword());
		supp.setAddress(data.getAddress());
		supp.setEmail(data.getEmail());
		supp.setId(data.getId());
		supp.setType(data.getType());
		supp.setVechime(data.getVechime());
		supp.setNumeFirma(data.getNumeFirma());
		supp.setVisibility(data.getVisibility());
 
		for(StockSupplier supplier : data.getStockSuppliers()) {
			supp.addStockSupplier(StockSupplierDao.toDTO(supplier));
		}
		
		return supp;
	}
	
	public static Supplier fromDTO(SupplierDTO data) {
		Supplier supp = new Supplier();
		 
		supp.setUsername(data.getUsername());
		supp.setPassword(data.getPassword());
		supp.setAddress(data.getAddress());
		supp.setEmail(data.getEmail());
		supp.setId(data.getId());
		supp.setType(data.getType());
		supp.setVechime(data.getVechime());
		supp.setNumeFirma(data.getNumeFirma());
		supp.setVisibility(data.getVisibility());
		
		for(StockSupplierDTO supplier : data.getStockSuppliers()) {
			supp.addStockSupplier(StockSupplierDao.fromDTO(supplier));
		}
		
		return supp;
	}
	
	@Override
	public void insert(SupplierDTO data) {
		entityManager.persist(fromDTO(data));  
		
	}

	@Override
	public void update(SupplierDTO data) {
		Supplier client = entityManager.find(Supplier.class, data.getId());
		if(client == null)
			return;
		
//		client.setNumeFirma(data.getNumeFirma());
//		client.setVechime(data.getVechime());
//		client.setVisibility(data.getVisibility()); 
//		client.setStockSuppliers(data.getStockSuppliers());
		 
		
		entityManager.merge(fromDTO(data));
		entityManager.flush();  
		
	}

	@Override
	public void delete(SupplierDTO data) {
		Supplier client = entityManager.find(Supplier.class, data.getId());  
		entityManager.remove(client); 
		
	}

	@Override
	public SupplierDTO getById(int id) {
		Supplier supplier = entityManager.find(Supplier.class, id);  
		return supplier == null? null : toDTO(supplier); 
	}  
}
