package com.example.ejb.daoRemote;

import java.util.List;

import javax.ejb.Remote;
 
import com.example.ejb.dto.WineDTO;
 

@Remote
public interface WineDAORemote extends GenericDAO<WineDTO>{ 
	public WineDTO getById(int id);
}
