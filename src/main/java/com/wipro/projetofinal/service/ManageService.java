package com.wipro.projetofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.CustomerRepository;
import com.wipro.projetofinal.repository.ManagerRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;

@Service
public class ManageService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;
	
	@Autowired
	private SpecialAccountRepository specialAccountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	public CheckingAccount salvarCheckingAccount(String registration, CheckingAccount account) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId()) == true) {
			return checkingAccountRepository.save(account);    //Se existir esse registro eu consigo salvar o "CheckingAccount".
		};
		
		return checkingAccountRepository.getById((long) 0);    // Se não existir simplesmente vai dar erro 500 na aplicação.   
	}
	
	
	
	public SpecialAccount salvarSpecialAccount(String registration, SpecialAccount account) {
		Manager manager = managerRepository.findByRegistration(registration);
		if(managerRepository.existsById(manager.getId()) == true) {
			return specialAccountRepository.save(account);    //Se existir esse registro eu consigo salvar o "CheckingAccount".
		};
		
		return specialAccountRepository.getById((long) 0);    // Se não existir simplesmente vai dar erro 500 na aplicação.   
	}
	
	
	
	
	public Manager salvarManager(Manager manager) {		
		return managerRepository.save(manager);
	}
	

}
