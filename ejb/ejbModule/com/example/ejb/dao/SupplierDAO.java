package com.example.ejb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.ejb.daoRemote.SupplierDAORemote;
import com.example.ejb.dto.SupplierDTO;
import com.example.ejb.model.Clientb2b;
import com.example.ejb.model.Supplier;

@Stateless
public class SupplierDAO implements SupplierDAORemote{

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
		
//		supp.setStockSuppliers(data.getStockSuppliers());
		
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
		
//		supp.setStockSuppliers(data.getStockSuppliers());
		
		return supp;
	}
	
	@Override
	public void insert(SupplierDTO data) {
		entityManager.persist(fromDTO(data));  
		
	}

	@Override
	public void update(SupplierDTO data) {
		Supplier client = entityManager.find(Supplier.class, data.getId());
 
		client.setNumeFirma(data.getNumeFirma());
		client.setVechime(data.getVechime());
		client.setVisibility(data.getVisibility()); 
//		client.setStockSuppliers(data.getStockSuppliers());
		 
		
		entityManager.merge(client);
		entityManager.flush();  
		
	}

	@Override
	public void delete(SupplierDTO data) {
		Supplier client = entityManager.find(Supplier.class, data.getId());  
		entityManager.remove(client); 
		
	}  
}
