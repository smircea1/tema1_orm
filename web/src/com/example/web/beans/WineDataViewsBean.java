package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.ejb.daoRemote.Clientb2bDAORemote;
import com.example.ejb.daoRemote.Clientb2cDAORemote;
import com.example.ejb.daoRemote.OrderDAORemote;
import com.example.ejb.daoRemote.OrderItemDAORemote;
import com.example.ejb.daoRemote.StockClientb2bDAORemote;
import com.example.ejb.daoRemote.StockSupplierDAORemote;
import com.example.ejb.daoRemote.SupplierDAORemote;
import com.example.ejb.daoRemote.WineDAORemote;
import com.example.ejb.dto.Clientb2bDTO;
import com.example.ejb.dto.Clientb2cDTO;
import com.example.ejb.dto.OrderDTO;
import com.example.ejb.dto.OrderItemDTO;
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
	OrderDAORemote dao_order;
	
	@EJB
	SupplierDAORemote dao_supplier;
	
	@EJB
	Clientb2bDAORemote dao_clientb2b;
	 
	@EJB
	Clientb2cDAORemote dao_clientb2c;
	
	@EJB
	OrderItemDAORemote dao_orderItem;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {

	}
	
	private SupplierDTO getSupplierLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("logged_user");
		return logged == null? null : dao_supplier.getById(logged.getId()); 
	}
	 
	private Clientb2cDTO getClientb2cLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("logged_user"); 
		return logged == null? null : dao_clientb2c.getById(logged.getId());  
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
	
	public void addClientStock(StockClientb2bDTO stock_wine) {
		Clientb2bDTO client = getClientb2bLogged(); 
		if(client == null)
			return;
			
		if(client.getId() == stock_wine.getClientb2b().getId())
			dao_clientb2b_wines.insert(stock_wine); 
	}
	public void updateClientStock(StockClientb2bDTO stock_wine) {
		Clientb2bDTO client = getClientb2bLogged(); 
		if(client == null || client.getId() != stock_wine.getClientb2b().getId())
			return;
		
		dao_clientb2b_wines.update(stock_wine);
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
	public List<StockClientb2bDTO> getAvailableB2CWines(){
		return dao_clientb2b_wines.getAllAvailableWines();
	}  
	
	public String addOrder(OrderItemDTO orderItem) {
		final FacesContext context = FacesContext.getCurrentInstance();
		
		if(orderItem.getCantitate() > orderItem.getStockClientb2b().getCantitate()) {
			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "stock request too big.", null ));
			return "failed";
		}
		Clientb2cDTO b2c_logged =  getClientb2cLogged();
		if(b2c_logged == null) {
			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "not logged..", null ));
			return "failed";
		}
		
		orderItem.setPret(orderItem.getCantitate() * orderItem.getStockClientb2b().getPret()); 

		int timestamp = 12324113; // hardcoded as f
		
		OrderDTO order = new OrderDTO();
		order.setDate(timestamp);  
		order.setClientb2c(b2c_logged);
		
		order.addItemOrderItem(orderItem); 
		 
		dao_order.insert(order);
//		for(OrderItemDTO order_itemDTO : order.getItems())
//			dao_orderItem.insert(order_itemDTO);
		 
		StockClientb2bDTO stock = orderItem.getStockClientb2b(); 
		stock.setCantitate(stock.getCantitate() - orderItem.getCantitate());
		dao_clientb2b_wines.update(stock);
		
		return "success";
	}
	
	public List<OrderItemDTO> getHistory(int id_user){ 
		return dao_orderItem.getHistory(id_user); 
	}
}
