package com.example.ejb.webDTO;
 

public class CreateAccountDTO { 

	private String username;
	private String password;
	private String email; 
	private String address;  
	private String dtype; 
	private String numeFirma;
	private String cui;
	private int vechime; 
	
	private String nume; 
	private String prenume;
	private String cnp;
	private java.util.Date dateRegister; 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getNumeFirma() {
		return numeFirma;
	}
	public void setNumeFirma(String numeFirma) {
		this.numeFirma = numeFirma;
	}
	public String getCui() {
		return cui;
	}
	public void setCui(String cui) {
		this.cui = cui;
	}
	public int getVechime() {
		return vechime;
	}
	public void setVechime(int vechime) {
		this.vechime = vechime;
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
	
}
