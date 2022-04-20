package com.wipro.projetofinal.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	protected static Integer count = 1;
	protected Integer accountNumber = 0;
	protected Double balance;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//exclui o user relacionado a Account no db.
    protected Customer customer;
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//exclui todas as movimentacoes relacionadas a Account no db.
	protected List<Moviment> moviment;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy",timezone = "GMT")
	protected Instant createdDate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//exclui o cartão relacionado a Account no db.
	@JoinColumn(name = "credit_id")
	protected CreditCard creditCard;

	public Account() {

	}

	public Account(Double balance,Instant createdDate) {
		this.accountNumber = count;
		count++;
		this.balance = balance;
		this.createdDate = createdDate;
		
	}


	public Integer getAccountNumber() {
		return accountNumber;
	}

	public Long getId() {
		return id;
	}

	public Double getBalance() {
		return balance;
	}

	public List<Moviment> getMoviment() {
		return moviment;
	}
	

	public User getCustomer() {
		return customer;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}

}