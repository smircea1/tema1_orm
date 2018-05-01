package com.example.ejb.daoRemote;

import java.util.List;

import com.example.ejb.dto.StockSupplierDTO;

public interface StockSupplierDAORemote extends GenericDAO<StockSupplierDTO> {
	List<StockSupplierDTO> getBySupplierId(int id);
	List<StockSupplierDTO> getByWineID(int id);
	List<StockSupplierDTO> getAllSuppliedWines(); 
}
