package com.example.ejb.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 

public class PromoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	private int id;
 
	private int dateEnd;
 
	private int dateStart;
 
	private String description;
	
	private double discount;

	private StockSupplierDTO stockSupplier;

	public PromoDto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(int dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(int dateStart) {
		this.dateStart = dateStart;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public StockSupplierDTO getStockSupplier() {
		return this.stockSupplier;
	}

	public void setStockSupplier(StockSupplierDTO stockSupplier) {
		this.stockSupplier = stockSupplier;
	}
}
