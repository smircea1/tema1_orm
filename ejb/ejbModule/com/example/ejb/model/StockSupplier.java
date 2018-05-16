package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stock_supplier database table.
 * 
 */
@Entity
@Table(name="stock_supplier")
@NamedQuery(name="StockSupplier.findAll", query="SELECT s FROM StockSupplier s")
public class StockSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double cantitate;

	private double pret;
 
	@OneToMany(mappedBy="stockSupplier")
	private List<Promo> promos;
 
	@OneToMany(mappedBy="stockSupplier")
	private List<StockClientb2b> stockClientb2bs;
 
	@ManyToOne
	private Supplier supplier;
 
	@ManyToOne
	private Wine wine;

	public StockSupplier() {
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

	public List<Promo> getPromos() {
		return this.promos;
	}

	public void setPromos(List<Promo> promos) {
		this.promos = promos;
	}

	public Promo addPromo(Promo promo) {
		getPromos().add(promo);
		promo.setStockSupplier(this);

		return promo;
	}

	public Promo removePromo(Promo promo) {
		getPromos().remove(promo);
		promo.setStockSupplier(null);

		return promo;
	}

	public List<StockClientb2b> getStockClientb2bs() {
		return this.stockClientb2bs;
	}

	public void setStockClientb2bs(List<StockClientb2b> stockClientb2bs) {
		this.stockClientb2bs = stockClientb2bs;
	}

	public StockClientb2b addStockClientb2b(StockClientb2b stockClientb2b) {
		getStockClientb2bs().add(stockClientb2b);
		stockClientb2b.setStockSupplier(this);

		return stockClientb2b;
	}

	public StockClientb2b removeStockClientb2b(StockClientb2b stockClientb2b) {
		getStockClientb2bs().remove(stockClientb2b);
		stockClientb2b.setStockSupplier(null);

		return stockClientb2b;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Wine getWine() {
		return this.wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}

}