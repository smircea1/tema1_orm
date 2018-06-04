package com.example.ejb.daoRemote;
 
import javax.ejb.Remote;

import com.example.ejb.dto.PromoDto;

@Remote
public interface PromoDaoRemote extends GenericDAO<PromoDto> {

}
