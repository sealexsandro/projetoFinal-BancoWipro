package com.wipro.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.CustomerService;
import com.wipro.projetofinal.service.ManageService;

import java.util.List;

@RestController
@RequestMapping(value = "/managers")
@CrossOrigin("*")
public class ManagerControler {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ManageService ManageService;


	@GetMapping("/checkingaccount/{registration}")
	public ResponseEntity<List<CheckingAccount>> GetAll(@PathVariable String registration) {
		List<CheckingAccount> list = ManageService.findAllChecking(registration);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Manager> salvarManager(@RequestBody Manager manager) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ManageService.salvarManager(manager));
	}

	@PostMapping("/checkingAccount/{registration}") // Feito, so passar para o de baixo
	public ResponseEntity<CheckingAccount> salvarCheckingAccount(@PathVariable String registration, @RequestBody CheckingAccount account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ManageService.salvarCheckingAccount(registration,account));
	}

	@PostMapping("/specialAccount/{registration}")
	public ResponseEntity<SpecialAccount> salvarSpecialAccount(@PathVariable String registration, @RequestBody SpecialAccount account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ManageService.salvarSpecialAccount(registration,account));
	}

	
}