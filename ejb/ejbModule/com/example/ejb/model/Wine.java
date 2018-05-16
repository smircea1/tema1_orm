package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the wine database table.
 * 
 */
@Entity
@NamedQuery(name="Wine.findAll", query="SELECT w FROM Wine w")
public class Wine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	@Lob
	private String name;

	@Lob
	private String soi;

	@Lob
	private String tip;

	private int year;
 
	@OneToMany(mappedBy="wine")
	private List<StockSupplier> stockSuppliers;

	public Wine() {
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

	public List<StockSupplier> getStockSuppliers() {
		return this.stockSuppliers;
	}

	public void setStockSuppliers(List<StockSupplier> stockSuppliers) {
		this.stockSuppliers = stockSuppliers;
	}

	public StockSupplier addStockSupplier(StockSupplier stockSupplier) {
		getStockSuppliers().add(stockSupplier);
		stockSupplier.setWine(this);

		return stockSupplier;
	}

	public StockSupplier removeStockSupplier(StockSupplier stockSupplier) {
		getStockSuppliers().remove(stockSupplier);
		stockSupplier.setWine(null);

		return stockSupplier;
	} 
}