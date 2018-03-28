package com.example.ejb.daoRemote;

import javax.ejb.Remote;

import com.example.ejb.dto.UserDTO;

@Remote
public interface UserDAORemote extends GenericDAO<UserDTO> { 
	public UserDTO findByUsername(String username);
}
