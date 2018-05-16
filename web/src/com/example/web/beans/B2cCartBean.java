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
	Clientb2cDAORemote dao_clientb2c;
	 
	@EJB
	StockClientb2bDAORemote dao_clientb2b_wines;

	@EJB
	OrderDAORemote dao_order; 
	

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
		UserDTO logged = (UserDTO) session.getAttribute("logged_user");
		return logged == null ? null : dao_clientb2c.getById(logged.getId());
	}

	public void createOrderItem(double quantity, StockClientb2bDTO ordered) {
		// todo, check if other other item is there, and sum the quantities,
		//then compare
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

		int timestamp = 12324113; // hardcoded as f
		order.setDate(timestamp);
		order.setClientb2c(b2c_logged);
		order.setOrderNo(UUID.randomUUID().toString());

		for (OrderItemDTO oid : daCart) {
			//update stock
			StockClientb2bDTO stock = oid.getStockClientb2b();
			stock.setCantitate(stock.getCantitate() - oid.getCantitate());
			dao_clientb2b_wines.update(stock);
			
			order.addItemOrderItem(oid);
			oid.setOrdermf(order);
			oid.setPret(stock.getPret());  
		}
		dao_order.insert(order);
		
		daCart.clear();
		updateSessionCart();

		return "success";
	}

}
