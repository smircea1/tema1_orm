package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the supplier database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier extends User implements Serializable {
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;

	@Lob
	@Column(name="nume_firma")
	private String numeFirma;

	private int vechime;

	private int visibility;

	//bi-directional many-to-one association to StockSupplier
	@OneToMany(mappedBy="supplier")
	private List<StockSupplier> stockSuppliers;

	public Supplier() {
	}

//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getNumeFirma() {
		return this.numeFirma;
	}

	public void setNumeFirma(String numeFirma) {
		this.numeFirma = numeFirma;
	}

	public int getVechime() {
		return this.vechime;
	}

	public void setVechime(int vechime) {
		this.vechime = vechime;
	}

	public int getVisibility() {
		return this.visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public List<StockSupplier> getStockSuppliers() {
		return this.stockSuppliers;
	}

	public void setStockSuppliers(List<StockSupplier> stockSuppliers) {
		this.stockSuppliers = stockSuppliers;
	}

	public StockSupplier addStockSupplier(StockSupplier stockSupplier) {
		getStockSuppliers().add(stockSupplier);
		stockSupplier.setSupplier(this);

		return stockSupplier;
	}

	public StockSupplier removeStockSupplier(StockSupplier stockSupplier) {
		getStockSuppliers().remove(stockSupplier);
		stockSupplier.setSupplier(null);

		return stockSupplier;
	}

}