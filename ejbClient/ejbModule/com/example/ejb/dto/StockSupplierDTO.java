package com.example.ejb.dto;

import java.io.Serializable;
import java.util.List;
 

public class StockSupplierDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private double cantitate;

	private double pret;

//	private List<Promo> promos;
 
	private List<StockClientb2bDTO> stockClientb2bs;

	private SupplierDTO supplier;

	private WineDTO wine;

	public StockSupplierDTO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCantitate() {
		return this.cantitate;
	}

	public void setCantitate(double cantitate) {
		this.cantitate = cantitate;
	}

	public double getPret() {
		return this.pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

//	public List<Promo> getPromos() {
//		return this.promos;
//	}
//
//	public void setPromos(List<Promo> promos) {
//		this.promos = promos;
//	}
//
//	public Promo addPromo(Promo promo) {
//		getPromos().add(promo);
//		promo.setStockSupplier(this);
//
//		return promo;
//	}
//
//	public Promo removePromo(Promo promo) {
//		getPromos().remove(promo);
//		promo.setStockSupplier(null);
//
//		return promo;
//	}

	public List<StockClientb2bDTO> getStockClientb2bs() {
		return this.stockClientb2bs;
	}

	public void setStockClientb2bs(List<StockClientb2bDTO> stockClientb2bs) {
		this.stockClientb2bs = stockClientb2bs;
	}

	public StockClientb2bDTO addStockClientb2b(StockClientb2bDTO stockClientb2b) {
		getStockClientb2bs().add(stockClientb2b);
		stockClientb2b.setStockSupplier(this);

		return stockClientb2b;
	}

	public StockClientb2bDTO removeStockClientb2b(StockClientb2bDTO stockClientb2b) {
		getStockClientb2bs().remove(stockClientb2b);
		stockClientb2b.setStockSupplier(null);

		return stockClientb2b;
	}

	public SupplierDTO getSupplier() {
		return this.supplier;
	}

	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}

	public WineDTO getWine() {
		return this.wine;
	}

	public void setWine(WineDTO wine) {
		this.wine = wine;
	}

}