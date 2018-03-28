package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the order_item database table.
 * 
 */
@Entity
@Table(name="order_item")
@NamedQuery(name="OrderItem.findAll", query="SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double cantitate;

	private double pret;

	//bi-directional many-to-one association to StockClientb2b
	@ManyToOne
	@JoinColumn(name="id_stockb2b")
	private StockClientb2b stockClientb2b;

	//bi-directional many-to-one association to Ordermf
	@ManyToOne
	@JoinColumn(name="id_order")
	private Ordermf ordermf;

	public OrderItem() {
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

	public StockClientb2b getStockClientb2b() {
		return this.stockClientb2b;
	}

	public void setStockClientb2b(StockClientb2b stockClientb2b) {
		this.stockClientb2b = stockClientb2b;
	}

	public Ordermf getOrdermf() {
		return this.ordermf;
	}

	public void setOrdermf(Ordermf ordermf) {
		this.ordermf = ordermf;
	}

}