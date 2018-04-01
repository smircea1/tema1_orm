package com.example.ejb.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDTO(){
		
	}
	
	public UserDTO(UserDTO copy){
		id = copy.id;
		username = copy.username;
		password = copy.password;
		type = copy.type;
		email = copy.email;
		address = copy.address;
	}
	public UserDTO(String username, String password, String type) {
		super();
		this.password = password;
		this.type = type;
		this.username = username;
	}

	public UserDTO(String address, String email, String password, String type, String username) {
		super();
		this.address = address;
		this.email = email;
		this.password = password;
		this.type = type;
		this.username = username;
	}

	public UserDTO(int id, String address, String email, String password, String type, String username) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.password = password;
		this.type = type;
		this.username = username;
	}

	private int id;

	private String address;

	private String email;

	private String password;

	private String type;

	private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
