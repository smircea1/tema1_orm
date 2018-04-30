package com.example.ejb.dto;

import java.io.Serializable;
 
public class OrderItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private int id;

	private double cantitate;

	private double pret;
 
	private StockClientb2bDTO stockClientb2b;
 
	private OrderDTO ordermf;

	public OrderItemDTO() {
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

	public StockClientb2bDTO getStockClientb2b() {
		return this.stockClientb2b;
	}

	public void setStockClientb2b(StockClientb2bDTO stockClientb2b) {
		this.stockClientb2b = stockClientb2b;
	}

	public OrderDTO getOrdermf() {
		return this.ordermf;
	}

	public void setOrdermf(OrderDTO ordermf) {
		this.ordermf = ordermf;
	}

}