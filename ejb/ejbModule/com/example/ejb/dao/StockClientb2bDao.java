package com.example.ejb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.StockClientb2bDAORemote;
import com.example.ejb.dto.StockClientb2bDTO;
import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.SupplierDTO;
import com.example.ejb.model.Clientb2c;
import com.example.ejb.model.OrderItem;
import com.example.ejb.model.Ordermf;
import com.example.ejb.model.StockClientb2b;
import com.example.ejb.model.StockSupplier;
import com.example.ejb.model.Supplier;

@Stateless
public class StockClientb2bDao implements StockClientb2bDAORemote {


	@PersistenceContext
	private EntityManager entityManager;
	
	
	public static StockClientb2bDTO toDTO(StockClientb2b data) {
		StockClientb2bDTO stock = new StockClientb2bDTO();
		StockSupplierDTO dtoStockSupplier = new StockSupplierDTO(); 
		SupplierDTO dtoSupplier = new SupplierDTO();   
		
		StockSupplier dataStock = data.getStockSupplier(); 
		Supplier dataSupplier = dataStock.getSupplier();
		
		dtoSupplier.setUsername(dataSupplier.getUsername());
		dtoSupplier.setPassword(dataSupplier.getPassword());
		dtoSupplier.setAddress(dataSupplier.getAddress());
		dtoSupplier.setEmail(dataSupplier.getEmail());
		dtoSupplier.setId(dataSupplier.getId());
		dtoSupplier.setType(dataSupplier.getType());
		dtoSupplier.setVechime(dataSupplier.getVechime());
		dtoSupplier.setNumeFirma(dataSupplier.getNumeFirma());
		dtoSupplier.setVisibility(dataSupplier.getVisibility());
		

		dtoStockSupplier.setSupplier(dtoSupplier);
		dtoStockSupplier.setCantitate(dataStock.getCantitate());
		dtoStockSupplier.setId(dataStock.getId());
		dtoStockSupplier.setPret(dataStock.getPret());
		dtoStockSupplier.setWine(WineDao.toDTO(dataStock.getWine()));
		
		
		stock.setCantitate(data.getCantitate());
		stock.setId(data.getId());
		stock.setPret(data.getPret());
		stock.setClientb2b(Clientb2bDao.toDTO(data.getClientb2b()));
		stock.setStockSupplier(dtoStockSupplier); 
		
		return stock;
	}
	
	public static StockClientb2b fromDTO(StockClientb2bDTO data) {
		StockClientb2b stock = new StockClientb2b();
		StockSupplier dtoStockSupplier = new StockSupplier(); 
		Supplier dtoSupplier = new Supplier();  
		
		StockSupplierDTO dataStock = data.getStockSupplier(); 
		SupplierDTO dataSupplier = dataStock.getSupplier();
		
		dtoSupplier.setUsername(dataSupplier.getUsername());
		dtoSupplier.setPassword(dataSupplier.getPassword());
		dtoSupplier.setAddress(dataSupplier.getAddress());
		dtoSupplier.setEmail(dataSupplier.getEmail());
		dtoSupplier.setId(dataSupplier.getId());
		dtoSupplier.setType(dataSupplier.getType());
		dtoSupplier.setVechime(dataSupplier.getVechime());
		dtoSupplier.setNumeFirma(dataSupplier.getNumeFirma());
		dtoSupplier.setVisibility(dataSupplier.getVisibility());
		

		dtoStockSupplier.setSupplier(dtoSupplier);
		dtoStockSupplier.setCantitate(dataStock.getCantitate());
		dtoStockSupplier.setId(dataStock.getId());
		dtoStockSupplier.setPret(dataStock.getPret());
		dtoStockSupplier.setWine(WineDao.fromDTO(dataStock.getWine()));
		
		
		stock.setCantitate(data.getCantitate());
		stock.setId(data.getId());
		stock.setPret(data.getPret());
		stock.setClientb2b(Clientb2bDao.fromDTO(data.getClientb2b()));
		stock.setStockSupplier(dtoStockSupplier); 
		
		return stock;
	}
	
	@Override
	public void insert(StockClientb2bDTO data) {
		entityManager.persist(fromDTO(data));  
	}

	@Override
	public void update(StockClientb2bDTO data) {
		StockClientb2b client = entityManager.find(StockClientb2b.class, data.getId());
		if(client == null)
			return;
		 
		entityManager.merge(fromDTO(data));
		entityManager.flush();   
	}

	@Override
	public void delete(StockClientb2bDTO data) {
		StockClientb2b stockClientb2b = entityManager.find(StockClientb2b.class, data.getId()); 
		entityManager.remove(stockClientb2b);   
	}

	@Override
	public StockClientb2bDTO getById(int id) {
		StockClientb2b client = entityManager.find(StockClientb2b.class, id);  
		return client == null? null : toDTO(client); 
	}

	@Override
	public List<StockClientb2bDTO> getByClientId(int id) {
		Query query = entityManager.createQuery("SELECT c FROM StockClientb2b c WHERE c.clientb2b.id = :id");
		query.setParameter("id", id);
		return toDTOList(query.getResultList()); 
	}
	
	private List<StockClientb2bDTO> toDTOList(List<?> objList){
		List<StockClientb2bDTO> result = new ArrayList<>();
		for(Object obj : objList) 
			result.add(toDTO((StockClientb2b) obj));
		
		return result;
	}

	@Override
	public List<StockClientb2bDTO> getAllAvailableWines() {
		Query query = entityManager.createQuery("SELECT c FROM StockClientb2b c ");
		return toDTOList(query.getResultList()); 
	}
}
