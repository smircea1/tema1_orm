package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the auto_restock database table.
 * 
 */
@Entity
@Table(name="auto_restock")
@NamedQuery(name="AutoRestock.findAll", query="SELECT a FROM AutoRestock a")
public class AutoRestock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int active;

	private int limited;
 
	@ManyToOne
	@JoinColumn(name="id_stock_clientb2b")
	private StockClientb2b stockClientb2b;

	public AutoRestock() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getLimited() {
		return this.limited;
	}

	public void setLimit(int limited) {
		this.limited = limited;
	}

	public StockClientb2b getStockClientb2b() {
		return this.stockClientb2b;
	}

	public void setStockClientb2b(StockClientb2b stockClientb2b) {
		this.stockClientb2b = stockClientb2b;
	}

}