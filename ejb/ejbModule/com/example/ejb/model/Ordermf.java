package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ordermf database table.
 * 
 */
@Entity
@NamedQuery(name="Ordermf.findAll", query="SELECT o FROM Ordermf o")
public class Ordermf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int date;

	@Lob
	private String orderNo;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="ordermf" )
	private List<OrderItem> orderItems;

	//bi-directional many-to-one association to Clientb2c
	@ManyToOne
	@JoinColumn(name="id_user")
	private Clientb2c clientb2c;

	public Ordermf() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setOrdermf(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setOrdermf(null);

		return orderItem;
	}

	public Clientb2c getClientb2c() {
		return this.clientb2c;
	}

	public void setClientb2c(Clientb2c clientb2c) {
		this.clientb2c = clientb2c;
	}

}