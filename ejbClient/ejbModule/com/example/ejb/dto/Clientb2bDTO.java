package com.example.ejb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

public class Clientb2bDTO  extends UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
  
	private String cui;
 
	private String numeFirma;

	private int subscribed;

	private int visibility;
 
	private List<StockClientb2bDTO> stockClientb2bs;

	public Clientb2bDTO() {
		stockClientb2bs = new ArrayList<>();
	} 
	
	public String getCui() {
		return this.cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public String getNumeFirma() {
		return this.numeFirma;
	}

	public void setNumeFirma(String numeFirma) {
		this.numeFirma = numeFirma;
	}

	public int getSubscribed() {
		return this.subscribed;
	}

	public void setSubscribed(int subscribed) {
		this.subscribed = subscribed;
	}

	public int getVisibility() {
		return this.visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public List<StockClientb2bDTO> getStockClientb2bs() {
		return this.stockClientb2bs;
	}

	public void setStockClientb2bs(List<StockClientb2bDTO> stockClientb2bs) {
		this.stockClientb2bs = stockClientb2bs;
	}

	public StockClientb2bDTO addStockClientb2b(StockClientb2bDTO stockClientb2b) {
		getStockClientb2bs().add(stockClientb2b);
		stockClientb2b.setClientb2b(this);

		return stockClientb2b;
	}

	public StockClientb2bDTO removeStockClientb2b(StockClientb2bDTO stockClientb2b) {
		getStockClientb2bs().remove(stockClientb2b);
		stockClientb2b.setClientb2b(null);

		return stockClientb2b;
	}

}