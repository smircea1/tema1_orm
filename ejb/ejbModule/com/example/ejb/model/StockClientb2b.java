package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stock_clientb2b database table.
 * 
 */
@Entity
@Table(name="stock_clientb2b")
@NamedQuery(name="StockClientb2b.findAll", query="SELECT s FROM StockClientb2b s")
public class StockClientb2b implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double cantitate;

	private double pret;

	//bi-directional many-to-one association to AutoRestock
	@OneToMany(mappedBy="stockClientb2b")
	private List<AutoRestock> autoRestocks;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="stockClientb2b")
	private List<OrderItem> orderItems;

	//bi-directional many-to-one association to Clientb2b
	@ManyToOne
	@JoinColumn(name="id_clientb2b")
	private Clientb2b clientb2b;

	//bi-directional many-to-one association to StockSupplier
	@ManyToOne
	@JoinColumn(name="id_stock_supplier")
	private StockSupplier stockSupplier;

	public StockClientb2b() {
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

	public List<AutoRestock> getAutoRestocks() {
		return this.autoRestocks;
	}

	public void setAutoRestocks(List<AutoRestock> autoRestocks) {
		this.autoRestocks = autoRestocks;
	}

	public AutoRestock addAutoRestock(AutoRestock autoRestock) {
		getAutoRestocks().add(autoRestock);
		autoRestock.setStockClientb2b(this);

		return autoRestock;
	}

	public AutoRestock removeAutoRestock(AutoRestock autoRestock) {
		getAutoRestocks().remove(autoRestock);
		autoRestock.setStockClientb2b(null);

		return autoRestock;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setStockClientb2b(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setStockClientb2b(null);

		return orderItem;
	}

	public Clientb2b getClientb2b() {
		return this.clientb2b;
	}

	public void setClientb2b(Clientb2b clientb2b) {
		this.clientb2b = clientb2b;
	}

	public StockSupplier getStockSupplier() {
		return this.stockSupplier;
	}

	public void setStockSupplier(StockSupplier stockSupplier) {
		this.stockSupplier = stockSupplier;
	}

}