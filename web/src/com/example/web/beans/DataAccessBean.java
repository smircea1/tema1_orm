package com.example.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.example.ejb.dto.WineDTO;

@ManagedBean(name = "dataAccessBean")
@RequestScoped
public class DataAccessBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {

	}
	
	// b2c
	public List<WineDTO> getAvailableB2CWines(){
		List<WineDTO> list = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			WineDTO wine = new WineDTO();
			wine.setId(i);
			wine.setName("custom b2c");
			wine.setYear(2018);
			wine.setSoi("Muscat");
			wine.setTip("inspirat");
			list.add(wine);
		}
		
		return list; 
	}
	
	// b2b
	
	public List<WineDTO> getB2BSuppliersWines(){
List<WineDTO> list = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			WineDTO wine = new WineDTO();
			wine.setId(i);
			wine.setName("custom b2b");
			wine.setYear(2018);
			wine.setSoi("Muscat");
			wine.setTip("supplied");
			list.add(wine);
		}
		
		return list; 
	}
	
	public List<WineDTO> getB2BWines(){
		List<WineDTO> list = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			WineDTO wine = new WineDTO();
			wine.setId(i);
			wine.setName("custom b2b");
			wine.setYear(2018);
			wine.setSoi("Muscat");
			wine.setTip("inspirat");
			list.add(wine);
		}
		
		return list; 
	}
}
