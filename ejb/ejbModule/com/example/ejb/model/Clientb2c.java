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

//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;

	@Lob
	private String cnp;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_register")
	private java.util.Date dateRegister; 

	@Lob
	private String nume;

	@Lob
	private String prenume;

	//bi-directional many-to-one association to Ordermf
	@OneToMany(mappedBy="clientb2c")
	private List<Ordermf> ordermfs;

	public Clientb2c() {
	}

//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getCnp() {
		return this.cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public java.util.Date getDateRegister() {
		return this.dateRegister;
	}

	public void setDateRegister(java.util.Date dateRegister) {
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