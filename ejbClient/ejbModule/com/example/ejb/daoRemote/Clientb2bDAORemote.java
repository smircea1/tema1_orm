package com.example.ejb.daoRemote;

import javax.ejb.Remote;

import com.example.ejb.dto.Clientb2bDTO; 

@Remote
public interface Clientb2bDAORemote  extends GenericDAO<Clientb2bDTO> {
	Clientb2bDTO findByCui(String cui);
}
