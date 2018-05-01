package com.example.ejb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

public class WineDTO implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private int id;
 
	private String description;
 
	private String name;
 
	private String soi;
 
	private String tip;

	private int year;

	private List<StockSupplierDTO> stockSuppliers;

	public WineDTO() {
		stockSuppliers = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSoi() {
		return this.soi;
	}

	public void setSoi(String soi) {
		this.soi = soi;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<StockSupplierDTO> getStockSuppliers() {
		return this.stockSuppliers;
	}

	public void setStockSuppliers(List<StockSupplierDTO> stockSuppliers) {
		this.stockSuppliers = stockSuppliers;
	}

	public StockSupplierDTO addStockSupplier(StockSupplierDTO stockSupplier) {
		getStockSuppliers().add(stockSupplier);
		stockSupplier.setWine(this);

		return stockSupplier;
	}

	public StockSupplierDTO removeStockSupplier(StockSupplierDTO stockSupplier) {
		getStockSuppliers().remove(stockSupplier);
		stockSupplier.setWine(null);

		return stockSupplier;
	} 
}
