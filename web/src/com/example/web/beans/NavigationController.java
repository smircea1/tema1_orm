package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.example.ejb.daoRemote.Clientb2bDAORemote;
import com.example.ejb.dto.Clientb2bDTO;
import com.example.ejb.dto.StockClientb2bDTO;
import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.UserDTO;
import com.example.ejb.dto.WineDTO; 


@ManagedBean(name = "navigationController")
@RequestScoped
public class NavigationController implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@EJB
	Clientb2bDAORemote dao_clientb2b;
	
	@PostConstruct
	public void init() {

	}
	 
	private Clientb2bDTO getClientb2bLogged() {
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		UserDTO logged = (UserDTO)session.getAttribute("logged_user"); 
		return logged == null? null : dao_clientb2b.getById(logged.getId());  
	}
	
	public String viewWine(WineDTO wine) {
		if(wine == null)
			return "failure";
		
		final FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("../wine_view.xhtml");
		} catch (IOException e) { 
			e.printStackTrace();
			return "failure";
		}
		
		return "success";
	}
	
	public String viewSuppliedWine(StockSupplierDTO wine) {
		return viewWine(wine.getWine());
	}
	
	public String addSuppliedWineToB2b(StockSupplierDTO supplied) {
		Clientb2bDTO logged = getClientb2bLogged();
		if(logged == null ){
			return "failure";
		}
		StockClientb2bDTO stock_b2b = new StockClientb2bDTO();
		stock_b2b.setStockSupplier(supplied);
		stock_b2b.setClientb2b(logged); 
		
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("pending_stock", stock_b2b);
		try {
			context.getExternalContext().redirect("./stock_add.xhtml");
		} catch (IOException e) { 
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}
	public String editClientb2bStock(StockClientb2bDTO stock) { 
		final FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("setted_edit_stock", stock);
		try {
			context.getExternalContext().redirect("./stock_edit.xhtml");
		} catch (IOException e) { 
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}
	
	public String editSuppliedWine(StockSupplierDTO wine) {
		final FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			context.getExternalContext().redirect("./edit_supplied_wine.xhtml");
		} catch (IOException e) { 
			e.printStackTrace();
			return "failure";
		}
		
		return "asd";
	}
	
}
