package com.example.ejb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private int date;
 
	private String orderNo;
 
	private Clientb2cDTO clientb2c;
	
	private List<OrderItemDTO> items;

	public OrderDTO(){
		items = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Clientb2cDTO getClientb2c() {
		return clientb2c;
	}

	public void setClientb2c(Clientb2cDTO clientb2c) {
		this.clientb2c = clientb2c;
	} 
	
	public List<OrderItemDTO> getItems(){
		return items;
	}
	
	public void addItemOrderItem(OrderItemDTO item) {
		items.add(item);
	}
	
	public void removeOrderItem(OrderItemDTO item) {
		items.remove(item);
	}
}
