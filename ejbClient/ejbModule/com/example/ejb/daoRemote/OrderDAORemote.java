package com.example.ejb.daoRemote;

import javax.ejb.Remote;

import com.example.ejb.dto.OrderDTO;

@Remote
public interface OrderDAORemote extends GenericDAO<OrderDTO> {

}
