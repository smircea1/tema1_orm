package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clientb2b database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="Clientb2b.findAll", query="SELECT c FROM Clientb2b c")
public class Clientb2b extends User implements Serializable {
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;

	@Lob
	private String cui;

	@Lob
	@Column(name="nume_firma")
	private String numeFirma;

	private int subscribed;

	private int visibility;

	//bi-directional many-to-one association to StockClientb2b
	@OneToMany(mappedBy="clientb2b")
	private List<StockClientb2b> stockClientb2bs;

	public Clientb2b() {
	}

//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getCui() {
		return this.cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public String getNumeFirma() {
		return this.numeFirma;
	}

	public void setNumeFirma(String numeFirma) {
		this.numeFirma = numeFirma;
	}

	public int getSubscribed() {
		return this.subscribed;
	}

	public void setSubscribed(int subscribed) {
		this.subscribed = subscribed;
	}

	public int getVisibility() {
		return this.visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public List<StockClientb2b> getStockClientb2bs() {
		return this.stockClientb2bs;
	}

	public void setStockClientb2bs(List<StockClientb2b> stockClientb2bs) {
		this.stockClientb2bs = stockClientb2bs;
	}

	public StockClientb2b addStockClientb2b(StockClientb2b stockClientb2b) {
		getStockClientb2bs().add(stockClientb2b);
		stockClientb2b.setClientb2b(this);

		return stockClientb2b;
	}

	public StockClientb2b removeStockClientb2b(StockClientb2b stockClientb2b) {
		getStockClientb2bs().remove(stockClientb2b);
		stockClientb2b.setClientb2b(null);

		return stockClientb2b;
	}

}