package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the promo database table.
 * 
 */
@Entity
@NamedQuery(name="Promo.findAll", query="SELECT p FROM Promo p")
public class Promo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="date_end")
	private int dateEnd;

	@Column(name="date_start")
	private int dateStart;

	private double discount;
 
	@ManyToOne
	@JoinColumn(name="stock_supplier_id")
	private StockSupplier stockSupplier;

	public Promo() {
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

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public StockSupplier getStockSupplier() {
		return this.stockSupplier;
	}

	public void setStockSupplier(StockSupplier stockSupplier) {
		this.stockSupplier = stockSupplier;
	}

}