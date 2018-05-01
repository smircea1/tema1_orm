package com.example.ejb.daoRemote;

import java.util.List;

import com.example.ejb.dto.StockClientb2bDTO;

public interface StockClientb2bDAORemote extends GenericDAO<StockClientb2bDTO> {
	public StockClientb2bDTO getById(int id);

	public List<StockClientb2bDTO> getByClientId(int id);
}
