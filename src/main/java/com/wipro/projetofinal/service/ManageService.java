package com.wipro.projetofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.repository.CustomerRepository;
import com.wipro.projetofinal.repository.ManagerRepository;

@Service
public class ManageService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private CustomerRepository repository;
	
	public Customer salvar(Customer customer) {
		return repository.save(customer);
	}
	

}
