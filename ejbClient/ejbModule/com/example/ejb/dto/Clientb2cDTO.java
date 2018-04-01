package com.example.ejb.dto;

import java.io.Serializable;

public class Clientb2cDTO extends UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
 
	private String cnp;
 
	private java.util.Date dateRegister;
 
	private String nume;
 
	private String prenume;

	public Clientb2cDTO(Clientb2cDTO data) {
		id = data.id;
		cnp = data.cnp;
		dateRegister = data.dateRegister;
		nume = data.nume;
		prenume = data.prenume;
	}
	

	public Clientb2cDTO() {

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public java.util.Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(java.util.Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	
}
