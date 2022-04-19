package com.wipro.projetofinal.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "tb_managers")
public class Manager extends User {

	private static final long serialVersionUID = 1L;

	private String registration;
	
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Manager(String name, String cpf, String email, String password, String registration) {
		super(name, cpf, email, password);
		this.registration = registration;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

}
