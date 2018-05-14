package com.example.ejb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.OrderItemDAORemote;
import com.example.ejb.dto.OrderDTO;
import com.example.ejb.dto.OrderItemDTO;
import com.example.ejb.dto.StockClientb2bDTO;
import com.example.ejb.model.OrderItem;
import com.example.ejb.model.Ordermf;
import com.example.ejb.model.StockClientb2b; 

@Stateless
public class OrderItemDao implements OrderItemDAORemote {
	@PersistenceContext
	private EntityManager entityManager;
	
	OrderItemDTO toDTO(OrderItem item) {
		OrderItemDTO dto = new OrderItemDTO();
		dto.setId(item.getId());
		dto.setCantitate(item.getCantitate());
		dto.setPret(item.getPret());
		dto.setStockClientb2b(StockClientb2bDao.toDTO(item.getStockClientb2b()));

		OrderDTO order = new OrderDTO();
		order.setClientb2c(Clientb2cDao.toDTO(item.getOrdermf().getClientb2c()));
		order.setDate(item.getOrdermf().getDate());
		order.setId(item.getOrdermf().getId());
		order.setOrderNo(item.getOrdermf().getOrderNo());

		dto.setOrdermf(order);
		
		return dto;
	}
	
	
	@Override
	public void insert(OrderItemDTO data) {
		OrderItem order_item = new OrderItem(); 
		
		Ordermf order = new Ordermf(); 
		order.setClientb2c(Clientb2cDao.fromDTO(data.getOrdermf().getClientb2c()));
		order.setDate(data.getOrdermf().getDate());
		order.setOrderNo(data.getOrdermf().getOrderNo()); 
		
		order_item.setCantitate(data.getCantitate());
		order_item.setId(data.getId());
		order_item.setOrdermf(order);
		order_item.setPret(data.getPret());
		order_item.setStockClientb2b(StockClientb2bDao.fromDTO(data.getStockClientb2b()));
		entityManager.persist(order_item); 
	}

	@Override
	public void update(OrderItemDTO data) { 
		OrderItem order_item = entityManager.find(OrderItem.class, data.getId());
		if(order_item == null)
			return; 
		
		Ordermf order = new Ordermf(); 
		order.setClientb2c(Clientb2cDao.fromDTO(data.getOrdermf().getClientb2c()));
		order.setDate(data.getOrdermf().getDate());
		order.setOrderNo(data.getOrdermf().getOrderNo()); 
		
		order_item.setCantitate(data.getCantitate());
		order_item.setId(data.getId());
		order_item.setOrdermf(order);
		order_item.setPret(data.getPret());
		order_item.setStockClientb2b(StockClientb2bDao.fromDTO(data.getStockClientb2b()));
		entityManager.persist(order_item);  
		
		entityManager.merge(order_item);
		entityManager.flush(); 
		
	}

	@Override
	public void delete(OrderItemDTO data) {
		OrderItem stockClientb2b = entityManager.find(OrderItem.class, data.getId()); 
		entityManager.remove(stockClientb2b);   
	}

	@Override
	public List<OrderItemDTO> getHistory(int id_user) {
		Query query = entityManager.createQuery("SELECT c FROM OrderItem c WHERE c.ordermf.clientb2c.id = :id");
		query.setParameter("id", id_user);
		return toDTOList(query.getResultList()); 
	}
 
	private List<OrderItemDTO> toDTOList(List<?> obj_list){
		List<OrderItemDTO> result = new ArrayList<>();
		for(Object obj : obj_list) 
			result.add(toDTO((OrderItem) obj));
		
		return result;
	}
	
}
