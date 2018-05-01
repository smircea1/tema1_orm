package com.example.ejb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

public class SupplierDTO extends UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
 
 
	private String numeFirma;

	private int vechime;

	private int visibility;
 
	private List<StockSupplierDTO> stockSuppliers;

	public SupplierDTO() {
		stockSuppliers = new ArrayList<>();
	}
  
	public String getNumeFirma() {
		return this.numeFirma;
	}

	public void setNumeFirma(String numeFirma) {
		this.numeFirma = numeFirma;
	}

	public int getVechime() {
		return this.vechime;
	}

	public void setVechime(int vechime) {
		this.vechime = vechime;
	}

	public int getVisibility() {
		return this.visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public List<StockSupplierDTO> getStockSuppliers() {
		return this.stockSuppliers;
	}

	public void setStockSuppliers(List<StockSupplierDTO> stockSuppliers) {
		this.stockSuppliers = stockSuppliers;
	}

	public StockSupplierDTO addStockSupplier(StockSupplierDTO stockSupplier) {
		getStockSuppliers().add(stockSupplier);
		stockSupplier.setSupplier(this);

		return stockSupplier;
	}

	public StockSupplierDTO removeStockSupplier(StockSupplierDTO stockSupplier) {
		getStockSuppliers().remove(stockSupplier);
		stockSupplier.setSupplier(null);

		return stockSupplier;
	} 
}