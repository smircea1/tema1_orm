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
		StockSupplierDTO dto_stock_supplier = new StockSupplierDTO(); // avoid stackoverflow
		SupplierDTO dto_supplier = new SupplierDTO();  // avoid stackoverflow
		
		StockSupplier data_stock = data.getStockSupplier(); 
		Supplier data_supplier = data_stock.getSupplier();
		
		dto_supplier.setUsername(data_supplier.getUsername());
		dto_supplier.setPassword(data_supplier.getPassword());
		dto_supplier.setAddress(data_supplier.getAddress());
		dto_supplier.setEmail(data_supplier.getEmail());
		dto_supplier.setId(data_supplier.getId());
		dto_supplier.setType(data_supplier.getType());
		dto_supplier.setVechime(data_supplier.getVechime());
		dto_supplier.setNumeFirma(data_supplier.getNumeFirma());
		dto_supplier.setVisibility(data_supplier.getVisibility());
		

		dto_stock_supplier.setSupplier(dto_supplier);
		dto_stock_supplier.setCantitate(data_stock.getCantitate());
		dto_stock_supplier.setId(data_stock.getId());
		dto_stock_supplier.setPret(data_stock.getPret());
		dto_stock_supplier.setWine(WineDao.toDTO(data_stock.getWine()));
		
		
		stock.setCantitate(data.getCantitate());
		stock.setId(data.getId());
		stock.setPret(data.getPret());
		stock.setClientb2b(Clientb2bDao.toDTO(data.getClientb2b()));
		stock.setStockSupplier(dto_stock_supplier);
//		stock.setStockSupplier(StockSupplierDao.toDTO(data.getStockSupplier()));
		
//		for(OrderItem ord : data.getOrderItems()) {
//			
//		}
		
		return stock;
	}
	
	public static StockClientb2b fromDTO(StockClientb2bDTO data) {
		StockClientb2b stock = new StockClientb2b();
		StockSupplier dto_stock_supplier = new StockSupplier(); // avoid stackoverflow
		Supplier dto_supplier = new Supplier();  // avoid stackoverflow
		
		StockSupplierDTO data_stock = data.getStockSupplier(); 
		SupplierDTO data_supplier = data_stock.getSupplier();
		
		dto_supplier.setUsername(data_supplier.getUsername());
		dto_supplier.setPassword(data_supplier.getPassword());
		dto_supplier.setAddress(data_supplier.getAddress());
		dto_supplier.setEmail(data_supplier.getEmail());
		dto_supplier.setId(data_supplier.getId());
		dto_supplier.setType(data_supplier.getType());
		dto_supplier.setVechime(data_supplier.getVechime());
		dto_supplier.setNumeFirma(data_supplier.getNumeFirma());
		dto_supplier.setVisibility(data_supplier.getVisibility());
		

		dto_stock_supplier.setSupplier(dto_supplier);
		dto_stock_supplier.setCantitate(data_stock.getCantitate());
		dto_stock_supplier.setId(data_stock.getId());
		dto_stock_supplier.setPret(data_stock.getPret());
		dto_stock_supplier.setWine(WineDao.fromDTO(data_stock.getWine()));
		
		
		stock.setCantitate(data.getCantitate());
		stock.setId(data.getId());
		stock.setPret(data.getPret());
		stock.setClientb2b(Clientb2bDao.fromDTO(data.getClientb2b()));
		stock.setStockSupplier(dto_stock_supplier);
//		stock.setStockSupplier(StockSupplierDao.toDTO(data.getStockSupplier()));
		
//		for(OrderItem ord : data.getOrderItems()) {
//			
//		}
		
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
		
//		client.setCantitate(data.getCantitate());
//		client.setPret(data.getPret());
//		client.setStockSupplier(StockSupplierDao.fromDTO(data.getStockSupplier()));
//		client.setClientb2b(Clientb2bDao.fromDTO(data.getClientb2b()));
//		client.setAutoRestocks(autoRestocks);
//		client.setOrderItems(orderItems);
		 
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
	
	private List<StockClientb2bDTO> toDTOList(List<?> obj_list){
		List<StockClientb2bDTO> result = new ArrayList<>();
		for(Object obj : obj_list) 
			result.add(toDTO((StockClientb2b) obj));
		
		return result;
	}

	@Override
	public List<StockClientb2bDTO> getAllAvailableWines() {
		Query query = entityManager.createQuery("SELECT c FROM StockClientb2b c ");
		return toDTOList(query.getResultList()); 
	}
}
