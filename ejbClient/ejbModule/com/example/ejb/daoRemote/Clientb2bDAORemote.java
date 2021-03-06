package com.example.ejb.daoRemote;

import java.util.List;

import javax.ejb.Remote;

import com.example.ejb.dto.Clientb2bDTO; 

@Remote
public interface Clientb2bDAORemote  extends GenericDAO<Clientb2bDTO> {
	public Clientb2bDTO findByCui(String cui);
	public Clientb2bDTO getById(int id);
	public List<Clientb2bDTO> getAllSubscribed();
}
