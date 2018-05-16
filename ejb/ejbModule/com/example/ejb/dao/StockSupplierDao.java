package com.example.ejb.dao;

import java.util.ArrayList;
import java.util.List; 

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.StockSupplierDAORemote;
import com.example.ejb.dto.StockClientb2bDTO;
import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.SupplierDTO;
import com.example.ejb.dto.WineDTO;
import com.example.ejb.model.StockClientb2b;
import com.example.ejb.model.StockSupplier;
import com.example.ejb.model.Supplier;

@Stateless
public class StockSupplierDao implements StockSupplierDAORemote {

	@PersistenceContext
	private EntityManager entityManager;

	public static StockSupplierDTO toDTO(StockSupplier data) {
		StockSupplierDTO suppl = new StockSupplierDTO(); 
		SupplierDTO dtoSupplier = new SupplierDTO(); 
		WineDTO dtoWine = new WineDTO(); 
		
		dtoWine.setId(data.getWine().getId());
		dtoWine.setName(data.getWine().getName());
		dtoWine.setSoi(data.getWine().getSoi());
		dtoWine.setYear(data.getWine().getYear());
		dtoWine.setTip(data.getWine().getTip());
		dtoWine.setDescription(data.getWine().getDescription());
		
		dtoSupplier.setId(data.getSupplier().getId());
		dtoSupplier.setUsername(data.getSupplier().getUsername());
		dtoSupplier.setAddress(data.getSupplier().getAddress());
		dtoSupplier.setEmail(data.getSupplier().getEmail());
		dtoSupplier.setNumeFirma(data.getSupplier().getNumeFirma());
		dtoSupplier.setVechime(data.getSupplier().getVechime());

		
		suppl.setCantitate(data.getCantitate());
		suppl.setPret(data.getPret());
		suppl.setSupplier(dtoSupplier);

		suppl.setWine(dtoWine);

		suppl.setId(data.getId());
		
		for(StockClientb2b stock : data.getStockClientb2bs()) {
			suppl.addStockClientb2b(StockClientb2bDao.toDTO(stock));
		}
		
		return suppl;
	}
	
	public static StockSupplier fromDTO(StockSupplierDTO data) {
		StockSupplier suppl = new StockSupplier();
		
		suppl.setCantitate(data.getCantitate());
		suppl.setPret(data.getPret());
		suppl.setSupplier(SupplierDao.fromDTO(data.getSupplier()));
		suppl.setWine(WineDao.fromDTO(data.getWine()));
		suppl.setId(data.getId());
		
		for(StockClientb2bDTO stock : data.getStockClientb2bs()) {
			suppl.addStockClientb2b(StockClientb2bDao.fromDTO(stock));
		}
		
		return suppl;
	}
	
	@Override
	public void insert(StockSupplierDTO data) {
		entityManager.persist(fromDTO(data));   
	}

	@Override
	public void update(StockSupplierDTO data) {
		StockSupplier stockSupplier = entityManager.find(StockSupplier.class, data.getId()); 
		if(stockSupplier == null)
			return;
		
		entityManager.merge(fromDTO(data));
		entityManager.flush(); 
	}

	@Override
	public void delete(StockSupplierDTO data) {
		StockSupplier stockSupplier = entityManager.find(StockSupplier.class, data.getId()); 
		entityManager.remove(stockSupplier);  
	}

	@Override
	public List<StockSupplierDTO> getBySupplierId(int id) {
		Query query = entityManager.createQuery("SELECT c FROM StockSupplier c WHERE c.supplier.id = :id");
		query.setParameter("id", id);
		return toDTOList(query.getResultList()); 
	}

	@Override
	public List<StockSupplierDTO> getByWineID(int id) {
		Query query = entityManager.createQuery("SELECT c FROM StockSupplier c WHERE c.wine.id = :id");
		query.setParameter("id", id); 
		return toDTOList(query.getResultList()); 
	}

	@Override
	public List<StockSupplierDTO> getAllSuppliedWines() {
		Query query = entityManager.createQuery("SELECT c FROM StockSupplier c"); 
		return toDTOList(query.getResultList()); 
	} 
	
	
	private List<StockSupplierDTO> toDTOList(List<?> objList){ 
		List<StockSupplierDTO> results = new ArrayList<>();
		for(Object obj : objList)
			results.add(toDTO((StockSupplier)obj));
		
		return results;
	}

}
