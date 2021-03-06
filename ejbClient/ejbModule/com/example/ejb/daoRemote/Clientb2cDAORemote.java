package com.example.ejb.daoRemote;

import javax.ejb.Remote;

import com.example.ejb.dto.Clientb2cDTO; 

@Remote
public interface Clientb2cDAORemote extends GenericDAO<Clientb2cDTO> {
	public Clientb2cDTO findByCnp(String cnp);
	public Clientb2cDTO getById(int id);
}
