package com.example.web.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.example.ejb.daoRemote.PromoDaoRemote;
import com.example.ejb.dto.PromoDto;
import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.UserDTO;


@ManagedBean(name = "promoBean")
@RequestScoped
public class PromoBean {

	String description;
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	@EJB
	PromoDaoRemote promoDaoRemote;
	
	public void insert(StockSupplierDTO stock) {
		insertPromo(stock, description);
	}
	
	void insertPromo(StockSupplierDTO stock, String promo_description) { 
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("loggedUser");
		
		if(logged.getType().compareTo("Supplier") == 0) {
			PromoDto dtoPromo = new PromoDto();
			dtoPromo.setDescription(promo_description);
			dtoPromo.setStockSupplier(stock);
			
			promoDaoRemote.insert(dtoPromo); 
		} 
	}
}
