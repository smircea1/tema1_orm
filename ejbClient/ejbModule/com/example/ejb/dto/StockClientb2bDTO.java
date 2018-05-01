package com.example.ejb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

public class StockClientb2bDTO implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private int id;

	private double cantitate;

	private double pret;
 
//	private List<AutoRestock> autoRestocks;
 
	private List<OrderItemDTO> orderItems;
 
	private Clientb2bDTO clientb2b;

	private StockSupplierDTO stockSupplier;

	public StockClientb2bDTO() {
		orderItems = new ArrayList<>();
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

//	public List<AutoRestock> getAutoRestocks() {
//		return this.autoRestocks;
//	}
//
//	public void setAutoRestocks(List<AutoRestock> autoRestocks) {
//		this.autoRestocks = autoRestocks;
//	}
//
//	public AutoRestock addAutoRestock(AutoRestock autoRestock) {
//		getAutoRestocks().add(autoRestock);
//		autoRestock.setStockClientb2b(this);
//
//		return autoRestock;
//	}
//
//	public AutoRestock removeAutoRestock(AutoRestock autoRestock) {
//		getAutoRestocks().remove(autoRestock);
//		autoRestock.setStockClientb2b(null);
//
//		return autoRestock;
//	}

	public List<OrderItemDTO> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItemDTO addOrderItem(OrderItemDTO orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setStockClientb2b(this);

		return orderItem;
	}

	public OrderItemDTO removeOrderItem(OrderItemDTO orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setStockClientb2b(null);

		return orderItem;
	}

	public Clientb2bDTO getClientb2b() {
		return this.clientb2b;
	}

	public void setClientb2b(Clientb2bDTO clientb2b) {
		this.clientb2b = clientb2b;
	}

	public StockSupplierDTO getStockSupplier() {
		return this.stockSupplier;
	}

	public void setStockSupplier(StockSupplierDTO stockSupplier) {
		this.stockSupplier = stockSupplier;
	}

}
