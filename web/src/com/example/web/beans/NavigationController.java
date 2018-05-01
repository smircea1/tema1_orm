package com.example.web.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.example.ejb.dto.StockSupplierDTO;
import com.example.ejb.dto.WineDTO; 


@ManagedBean(name = "navigationController")
@RequestScoped
public class NavigationController implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {

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
		
		//go to add page
		
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
