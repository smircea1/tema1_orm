package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	WineDAORemote daoWine;
	
	@EJB
	StockSupplierDAORemote daoStockSupplier;
	
	@EJB
	StockClientb2bDAORemote daoStockClientb2b;

	@EJB
	OrderDAORemote daoOrder;

	@EJB
	OrderItemDAORemote daoOrderItem;
	
	@EJB
	SupplierDAORemote daoSupplier;
	
	@EJB
	Clientb2bDAORemote daoClientb2b;
	 
	@EJB
	Clientb2cDAORemote daoClientb2c;
	
	
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
		UserDTO logged = (UserDTO)session.getAttribute("loggedUser");
		return logged == null? null : daoSupplier.getById(logged.getId()); 
	}
	 
	private Clientb2cDTO getClientb2cLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("loggedUser"); 
		return logged == null? null : daoClientb2c.getById(logged.getId());  
	}
	
	private Clientb2bDTO getClientb2bLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("loggedUser"); 
		return logged == null? null : daoClientb2b.getById(logged.getId());  
	}
	 
	public List<StockSupplierDTO> getSupplierWines(){
		SupplierDTO supplier = getSupplierLogged();
		return supplier == null? null : daoStockSupplier.getBySupplierId(supplier.getId());
	} 
	
	public void removeSuppliedWine(StockSupplierDTO data) { 
		SupplierDTO supplier = getSupplierLogged();
		if(data.getSupplier().getId() == supplier.getId())
			daoStockSupplier.delete(data);
	}
	
	public List<StockSupplierDTO> getB2BSuppliersWines(){
		SupplierDTO supplier = getSupplierLogged();
		return supplier == null? null : daoStockSupplier.getBySupplierId(supplier.getId()); 
	}
	 
	public List<StockSupplierDTO> getAllSuppliersWines(){
		return daoStockSupplier.getAllSuppliedWines();
	}
	
	public void addClientStock(StockClientb2bDTO stockWine) {
		Clientb2bDTO client = getClientb2bLogged(); 
		if(client == null)
			return;
			
		if(client.getId() == stockWine.getClientb2b().getId())
			daoStockClientb2b.insert(stockWine); 
	}
	public void updateClientStock(StockClientb2bDTO stockWine) {
		Clientb2bDTO client = getClientb2bLogged(); 
		if(client == null || client.getId() != stockWine.getClientb2b().getId())
			return;
		
		daoStockClientb2b.update(stockWine);
	}
	
	public List<StockClientb2bDTO> getB2BWines(){ 
		Clientb2bDTO client = getClientb2bLogged();
		return client == null? null : daoStockClientb2b.getByClientId(client.getId());  
	}
	
	
	public void removeB2bWine(StockClientb2bDTO stockWine) { 
		Clientb2bDTO client = getClientb2bLogged();
		if(client == null)
			return;
		
		daoStockClientb2b.delete(stockWine); 

		final FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) { 
			e.printStackTrace();
		} 
	} 
 
	public List<StockClientb2bDTO> getAvailableB2CWines(){
		return daoStockClientb2b.getAllAvailableWines();
	}  
	
	public String addOrder(OrderItemDTO orderItem) {
		final FacesContext context = FacesContext.getCurrentInstance();
		
		if(orderItem.getCantitate() > orderItem.getStockClientb2b().getCantitate()) {
			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "stock request too big.", null ));
			return "failed";
		}
		Clientb2cDTO b2cLogged =  getClientb2cLogged();
		if(b2cLogged == null) {
			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "not logged..", null ));
			return "failed";
		}
		
		orderItem.setPret(orderItem.getCantitate() * orderItem.getStockClientb2b().getPret()); 

		int timestamp = (int) System.currentTimeMillis(); 
		
		OrderDTO order = new OrderDTO();
		order.setDate(timestamp);  
		order.setClientb2c(b2cLogged);
		order.setOrderNo(UUID.randomUUID().toString());
		
		order.addItemOrderItem(orderItem); 
		 
		daoOrder.insert(order);  
		
		StockClientb2bDTO stock = orderItem.getStockClientb2b(); 
		stock.setCantitate(stock.getCantitate() - orderItem.getCantitate());
		daoStockClientb2b.update(stock);
		
		return "success";
	}
	
	public String placeOrder(List<OrderItemDTO> orderItems) {
		final FacesContext context = FacesContext.getCurrentInstance();
		
		Clientb2cDTO b2cLogged =  getClientb2cLogged();
		if(b2cLogged == null) {
			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "not logged..", null ));
			return "failed";
		}
		
		OrderDTO order = new OrderDTO();
		
		int timestamp = (int) System.currentTimeMillis();  
		order.setDate(timestamp);  
		order.setClientb2c(b2cLogged);
		order.setOrderNo(UUID.randomUUID().toString());
		
		for(OrderItemDTO oid : orderItems) 
			order.addItemOrderItem(oid);
		 
		daoOrder.insert(order);
		for(OrderItemDTO oid : orderItems) {
			StockClientb2bDTO stock = oid.getStockClientb2b(); 
			stock.setCantitate(stock.getCantitate() - oid.getCantitate());
			daoStockClientb2b.update(stock);
		}
		 
		return "success";
	}
	
	public List<OrderItemDTO> getHistory(int idUser){ 
		return daoOrderItem.getHistory(idUser); 
	}
}
