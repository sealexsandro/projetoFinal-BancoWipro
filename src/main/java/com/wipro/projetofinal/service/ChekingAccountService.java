package com.wipro.projetofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;

@Service
public class ChekingAccountService {

	
	@Autowired
	private CheckingAccountRepository repository;
	
	public CheckingAccount salvar(CheckingAccount checkingAccount) {
		return repository.save(checkingAccount);
	}
}
