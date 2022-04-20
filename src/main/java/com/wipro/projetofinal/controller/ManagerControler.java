package com.wipro.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.CustomerService;
import com.wipro.projetofinal.service.ManageService;

@RestController
@RequestMapping(value = "/managers")
@CrossOrigin("*")
public class ManagerControler {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ManageService ManageService;
	
	
	@PostMapping
	public ResponseEntity<Manager> salvarManager(@RequestBody Manager manager) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ManageService.salvarManager(manager));
	}
	
	
	@PostMapping("/{registration}") // Feito, so passar para o de baixo
	public ResponseEntity<CheckingAccount> salvarCheckingAccount(@PathVariable String registration, @RequestBody CheckingAccount account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ManageService.salvarCheckingAccount(registration,account));
	}
	
	
	@PostMapping("/specialAccount/{registration}")
	public ResponseEntity<SpecialAccount> salvarSpecialAccount(@PathVariable String registration, @RequestBody SpecialAccount account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ManageService.salvarSpecialAccount(registration,account));
	}
	
	
}