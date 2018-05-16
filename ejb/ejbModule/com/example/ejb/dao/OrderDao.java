package com.example.ejb.dao;
 

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.ejb.daoRemote.OrderDAORemote; 
import com.example.ejb.dto.OrderDTO;
import com.example.ejb.dto.OrderItemDTO;
import com.example.ejb.model.Clientb2c;
import com.example.ejb.model.OrderItem;
import com.example.ejb.model.Ordermf; 


/**
 * Session Bean implementation class FirstStatelessEjb
 */

@Stateless
public class OrderDao implements OrderDAORemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	public static OrderDTO toDTO(Ordermf data) {
		OrderDTO dto = new OrderDTO();
		
		dto.setId(data.getId());
		dto.setDate(data.getDate());
		dto.setOrderNo(data.getOrderNo()); 	
		dto.setClientb2c(Clientb2cDao.toDTO(data.getClientb2c()));
		
		return dto;
	} 
	
	@Override
	public void insert(OrderDTO data) { 
		Clientb2c client = entityManager.find(Clientb2c.class, data.getClientb2c().getId());  
		System.out.println("client id = " + client.getId());

		List<OrderItem> orderItems = new ArrayList<>();
		
		Ordermf order = new Ordermf(); 
		order.setClientb2c(client);
		order.setDate(data.getDate());
		order.setOrderNo(data.getOrderNo()); 
		
		for(OrderItemDTO item : data.getItems()) { 
			OrderItem dto = new OrderItem();
			dto.setId(item.getId());
			dto.setCantitate(item.getCantitate());
			dto.setPret(item.getPret());
			dto.setStockClientb2b(StockClientb2bDao.fromDTO(item.getStockClientb2b()));
			dto.setOrdermf(order);
			orderItems.add(dto);
		} 
		order.setOrderItems(orderItems);
		entityManager.persist(order); 
	}

	@Override
	public void update(OrderDTO data) {
		Ordermf order = entityManager.find(Ordermf.class, data.getId());
		Clientb2c client = entityManager.find(Clientb2c.class, data.getClientb2c().getId());
		 
		order.setDate(data.getDate());
		order.setOrderNo(data.getOrderNo()); 	
		order.setClientb2c(client);
		
		entityManager.merge(order); 
		entityManager.flush();
	}

	@Override
	public void delete(OrderDTO data) {
		Ordermf order = entityManager.find(Ordermf.class, data.getId()); 
		entityManager.remove(order);   
	}

	@Override
	public OrderDTO getByOrderNumber(String number) {
		Query query = entityManager.createQuery("SELECT c FROM Ordermf c WHERE c.orderNo = :orderNo");
		query.setParameter("orderNo", number); 
		
		List<?> result = query.getResultList();
		if(result.size() == 0)
			return null;
		return toDTO((Ordermf)result.get(0));
	}

}
