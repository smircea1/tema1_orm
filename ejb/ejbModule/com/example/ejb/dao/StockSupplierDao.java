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
		SupplierDTO dto_supplier = new SupplierDTO(); // avoid stackoverflow
		WineDTO dto_wine = new WineDTO(); // avoid stackoverflow
		
		dto_wine.setId(data.getWine().getId());
		dto_wine.setName(data.getWine().getName());
		dto_wine.setSoi(data.getWine().getSoi());
		dto_wine.setYear(data.getWine().getYear());
		dto_wine.setTip(data.getWine().getTip());
		dto_wine.setDescription(data.getWine().getDescription());
		
		dto_supplier.setId(data.getSupplier().getId());
		dto_supplier.setUsername(data.getSupplier().getUsername());
		dto_supplier.setAddress(data.getSupplier().getAddress());
		dto_supplier.setEmail(data.getSupplier().getEmail());
		dto_supplier.setNumeFirma(data.getSupplier().getNumeFirma());
		dto_supplier.setVechime(data.getSupplier().getVechime());
		//bla bla.
		
		suppl.setCantitate(data.getCantitate());
		suppl.setPret(data.getPret());
		suppl.setSupplier(dto_supplier);
//		suppl.setSupplier(SupplierDAO.toDTO(data.getSupplier()));
		suppl.setWine(dto_wine);
//		suppl.setWine(WineDao.toDTO(data.getWine()));
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
		suppl.setSupplier(SupplierDAO.fromDTO(data.getSupplier()));
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
		
//		stockSupplier.setCantitate(data.getCantitate());
//		stockSupplier.setPret(data.getPret());
//		stockSupplier.setWine(WineDao.fromDTO(data.getWine()));
//		stockSupplier.setSupplier(SupplierDAO.fromDTO(data.getSupplier()));
//		
//		List<StockClientb2b> stockClientb2bs = new ArrayList<>();
//		
//		for(StockClientb2bDTO client : data.getStockClientb2bs()) 
//			stockClientb2bs.add(StockClientb2bDao.fromDTO(client));
//		 
//		stockSupplier.setStockClientb2bs(stockClientb2bs);

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
	
	
	private List<StockSupplierDTO> toDTOList(List<?> obj_list){ 
		List<StockSupplierDTO> results = new ArrayList<>();
		for(Object obj : obj_list)
			results.add(toDTO((StockSupplier)obj));
		
		return results;
	}

}
