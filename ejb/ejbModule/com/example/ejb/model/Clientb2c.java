package com.example.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clientb2c database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="Clientb2c.findAll", query="SELECT c FROM Clientb2c c")
public class Clientb2c extends User implements Serializable {
	private static final long serialVersionUID = 1L;
  
	@Lob
	private String cnp;

	@Column(name="date_register")
	private int dateRegister;

	@Lob
	private String nume;

	@Lob
	private String prenume;

	//bi-directional many-to-one association to Ordermf
	@OneToMany(mappedBy="clientb2c")
	private List<Ordermf> ordermfs;

	public Clientb2c() {
	}

	public String getCnp() {
		return this.cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public int getDateRegister() {
		return this.dateRegister;
	}

	public void setDateRegister(int dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return this.prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public List<Ordermf> getOrdermfs() {
		return this.ordermfs;
	}

	public void setOrdermfs(List<Ordermf> ordermfs) {
		this.ordermfs = ordermfs;
	}

	public Ordermf addOrdermf(Ordermf ordermf) {
		getOrdermfs().add(ordermf);
		ordermf.setClientb2c(this);

		return ordermf;
	}

	public Ordermf removeOrdermf(Ordermf ordermf) {
		getOrdermfs().remove(ordermf);
		ordermf.setClientb2c(null);

		return ordermf;
	}

}