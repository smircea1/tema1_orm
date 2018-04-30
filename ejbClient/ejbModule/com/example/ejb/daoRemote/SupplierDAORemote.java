package com.example.ejb.daoRemote;

import javax.ejb.Remote;
 
import com.example.ejb.dto.SupplierDTO;

@Remote
public interface SupplierDAORemote  extends GenericDAO<SupplierDTO> {

} 
