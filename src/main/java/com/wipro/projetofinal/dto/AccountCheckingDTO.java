package com.wipro.projetofinal.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Moviment;

public class AccountCheckingDTO {

	protected String accountNumber;
	protected Double balance;
	
    protected Customer customer;
    
	protected List<Moviment> moviment;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT-3")
	protected Instant createdDate;
	
	
	public AccountCheckingDTO(CheckingAccount account) {
		this.accountNumber = account.getAccountNumber();
		this.balance = account.getBalance();
		this.customer =  (Customer) account.getCustomer();
		this.createdDate = account.getCreatedDate();
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public Double getBalance() {
		return balance;
	}


	public Customer getCustomer() {
		return customer;
	}


	public List<Moviment> getMoviment() {
		return moviment;
	}


	public Instant getCreatedDate() {
		return createdDate;
	}
	
	
	
}
