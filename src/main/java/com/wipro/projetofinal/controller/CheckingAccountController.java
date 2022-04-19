package com.wipro.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.service.ChekingAccountService;

@RestController
@RequestMapping(value = "/ch")
@CrossOrigin("*")
public class CheckingAccountController {

	@Autowired
	private ChekingAccountService service;
	
	
	@PostMapping
	public ResponseEntity<CheckingAccount> salvar(@RequestBody CheckingAccount checkingAccount) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(checkingAccount));
	}
}
