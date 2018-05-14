package com.example.ejb.daoRemote;

import java.util.List;

import javax.ejb.Remote; 
import com.example.ejb.dto.OrderItemDTO;

@Remote
public interface OrderItemDAORemote extends GenericDAO<OrderItemDTO> { 
	List<OrderItemDTO> getHistory(int id_user); 
}
