package com.example.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.example.ejb.daoRemote.Clientb2cDAORemote;
import com.example.ejb.daoRemote.OrderDAORemote;
import com.example.ejb.daoRemote.StockClientb2bDAORemote;
import com.example.ejb.dto.Clientb2cDTO;
import com.example.ejb.dto.OrderDTO;
import com.example.ejb.dto.OrderItemDTO;
import com.example.ejb.dto.StockClientb2bDTO;
import com.example.ejb.dto.UserDTO;
import com.example.ejb.webDTO.ChangePasswordDTO;

@ManagedBean(name = "b2ccartbean")
@RequestScoped
public class B2cCartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@EJB
	Clientb2cDAORemote daoClientb2c;
	 
	@EJB
	StockClientb2bDAORemote daoClientb2bWines;

	@EJB
	OrderDAORemote daoOrder; 
	

	private List<OrderItemDTO> daCart;
	
	@PostConstruct
	public void init() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		daCart = (List<OrderItemDTO>) session.getAttribute("cart"); 
		if(daCart == null) {
			daCart = new ArrayList<>();
		}
	}
	
	private void updateSessionCart() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("cart", daCart);
	}
	
	public B2cCartBean() {
		
	}


	private Clientb2cDTO getClientb2cLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO) session.getAttribute("loggedUser");
		return logged == null ? null : daoClientb2c.getById(logged.getId());
	}

	public void createOrderItem(double quantity, StockClientb2bDTO ordered) { 
		OrderItemDTO alreadyExists = null;
		for(OrderItemDTO test : daCart) {
			if(test.getStockClientb2b().getId() == ordered.getId()) {
				alreadyExists = test;
				break;
			} 
		} 
		
		if(alreadyExists != null) {
			double newQuantity = quantity + alreadyExists.getCantitate();
			if(newQuantity > ordered.getCantitate())
				alreadyExists.setCantitate(newQuantity);
			return;
		}
		
		if(quantity > ordered.getCantitate()) {
			return;
		}
		OrderItemDTO orderItem = new OrderItemDTO();
		orderItem.setCantitate(quantity);
		orderItem.setStockClientb2b(ordered); 
 
		daCart.add(orderItem);
		updateSessionCart();
	} 

	public String placeOrder() {
		final FacesContext context = FacesContext.getCurrentInstance();

		Clientb2cDTO b2c_logged = getClientb2cLogged();
		if (b2c_logged == null) {
			context.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "not logged..", null));
			return "failed";
		}

		OrderDTO order = new OrderDTO();

		int timestamp = (int)System.currentTimeMillis(); 
		order.setDate(timestamp);
		order.setClientb2c(b2c_logged);
		order.setOrderNo(UUID.randomUUID().toString());

		for (OrderItemDTO oid : daCart) { 
			StockClientb2bDTO stock = oid.getStockClientb2b();
			stock.setCantitate(stock.getCantitate() - oid.getCantitate());
			daoClientb2bWines.update(stock);
			
			order.addItemOrderItem(oid);
			oid.setOrdermf(order);
			oid.setPret(stock.getPret());  
		}
		daoOrder.insert(order);
		
		daCart.clear();
		updateSessionCart();

		return "success";
	}

}
