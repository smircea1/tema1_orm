package com.example.ejb.dao;
 

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 

import com.example.ejb.daoRemote.OrderDAORemote; 
import com.example.ejb.dto.OrderDTO;
import com.example.ejb.model.Clientb2c;
import com.example.ejb.model.Ordermf; 


/**
 * Session Bean implementation class FirstStatelessEjb
 */

@Stateless
public class OrderDao implements OrderDAORemote {

	@PersistenceContext
	private EntityManager entityManager;
	
//	public static OrderDTO toDTO(Ordermf data) {
//		OrderDTO
//	}
//	
//	public static Ordermf fromDTO(OrderDTO data) {
//		
//	}
	
	@Override
	public void insert(OrderDTO data) { 
		Clientb2c client = entityManager.find(Clientb2c.class, data.getClientb2c().getId());  
		System.out.println("client id = " + client.getId());
		
		Ordermf order = new Ordermf(); 
		order.setClientb2c(client);
		order.setDate(data.getDate());
		order.setOrderNo(data.getOrderNo()); 
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

}
