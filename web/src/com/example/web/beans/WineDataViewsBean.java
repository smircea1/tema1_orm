package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.ejb.daoRemote.Clientb2bDAORemote;
import com.example.ejb.daoRemote.StockClientb2bDAORemote;
import com.example.ejb.daoRemote.StockSupplierDAORemote;
import com.example.ejb.daoRemote.SupplierDAORemote;
import com.example.ejb.daoRemote.WineDAORemote;
import com.example.ejb.dto.Clientb2bDTO;
import com.example.ejb.dto.StockClientb2bDTO;
import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.SupplierDTO;
import com.example.ejb.dto.UserDTO;
import com.example.ejb.dto.WineDTO;

@ManagedBean(name = "wineDataViewsBean")
@RequestScoped
public class WineDataViewsBean implements Serializable {

	@EJB
	WineDAORemote dao_wine;
	
	@EJB
	StockSupplierDAORemote dao_supplier_wines;
	
	@EJB
	StockClientb2bDAORemote dao_clientb2b_wines;

	@EJB
	SupplierDAORemote dao_supplier;
	
	@EJB
	Clientb2bDAORemote dao_clientb2b;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {

	}
	
	private List<WineDTO> getDummyWinesList(){
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
	
	private SupplierDTO getSupplierLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("logged_user");
		return logged == null? null : dao_supplier.getById(logged.getId()); 
	}
	
	private Clientb2bDTO getClientb2bLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("logged_user"); 
		return logged == null? null : dao_clientb2b.getById(logged.getId());  
	}
	 
	public List<StockSupplierDTO> getSupplierWines(){
		SupplierDTO supplier = getSupplierLogged();
		return supplier == null? null : dao_supplier_wines.getBySupplierId(supplier.getId());
	}
	
	// SUPPLIER
	
	public void removeSuppliedWine(StockSupplierDTO data) { 
		SupplierDTO supplier = getSupplierLogged();
		if(data.getSupplier().getId() == supplier.getId())
			dao_supplier_wines.delete(data);
	}
	
	public List<StockSupplierDTO> getB2BSuppliersWines(){
		SupplierDTO supplier = getSupplierLogged();
		return supplier == null? null : dao_supplier_wines.getBySupplierId(supplier.getId()); 
	}
	
	//B2B
	public List<StockSupplierDTO> getAllSuppliersWines(){
		return dao_supplier_wines.getAllSuppliedWines();
	}
	
	public void addSupliedWineToB2b(StockClientb2bDTO stock_wine) {
		Clientb2bDTO client = getClientb2bLogged(); 
		if(client == null)
			return;
			
		if(client.getId() == stock_wine.getClientb2b().getId())
			dao_clientb2b_wines.insert(stock_wine);
			
	}
	
	public List<StockClientb2bDTO> getB2BWines(){ 
		Clientb2bDTO client = getClientb2bLogged();
		return client == null? null : dao_clientb2b_wines.getByClientId(client.getId());  
	}
	
	
	public void removeB2bWine(StockClientb2bDTO stock_wine) { 
		Clientb2bDTO client = getClientb2bLogged();
		if(client == null)
			return;
		
		dao_clientb2b_wines.delete(stock_wine); 
		//refresh
		final FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) { 
			e.printStackTrace();
		} 
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
}
