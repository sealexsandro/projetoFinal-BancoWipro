package com.wipro.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wipro.projetofinal.dto.AccountCheckingDTO;
import com.wipro.projetofinal.dto.AccountSpecialDTO;
import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.CreditCard;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.CustomerService;
import com.wipro.projetofinal.service.ManageService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/managers")
@CrossOrigin("*")
public class ManagerControler {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ManageService manageService;

	@PostMapping
	public ResponseEntity<Manager> saveManager(@RequestBody Manager manager)
			throws Exception, SQLIntegrityConstraintViolationException {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveManager(manager));
	}

	@GetMapping("/checkingAccount/{registration}/{accountNumber}")
	public ResponseEntity<CheckingAccount> getByAccountNumberChecking(@PathVariable String registration,
			@PathVariable String accountNumber) {
		CheckingAccount obj = manageService.findByAccountNumberChecking(registration, accountNumber);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/specialAccount/{registration}/{accountNumber}")
	public ResponseEntity<SpecialAccount> getByAccountNumberSpecial(@PathVariable String registration,
			@PathVariable String accountNumber) {
		SpecialAccount obj = manageService.findByAccountNumberSpecial(registration, accountNumber);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/accountAll/{registration}")
	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable String registration) {
		List<Account> accounts = manageService.findAllAccounts(registration);
		return ResponseEntity.ok().body(accounts);
	}

	@GetMapping("/checkingAccount/{registration}")
	public ResponseEntity<List<CheckingAccount>> getAllChecking(@PathVariable String registration) {
		List<CheckingAccount> list = manageService.findAllChecking(registration);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/specialAccount/{registration}")
	public ResponseEntity<List<SpecialAccount>> getAllSpecial(@PathVariable String registration) {
		List<SpecialAccount> list = manageService.findAllSpecial(registration);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/checkingAccount/{registration}") // Feito, so passar para o de baixo
	public ResponseEntity<AccountCheckingDTO> salvarCheckingAccount(@PathVariable String registration,
			@RequestBody CheckingAccount account, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveCheckingAccount(registration, account));
	}

	@PostMapping("/specialAccount/{registration}")
	public ResponseEntity<AccountSpecialDTO> salvarSpecialAccount(@PathVariable String registration,
			@RequestBody SpecialAccount account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(manageService.saveSpecialAccount(registration, account));
	}

	@PutMapping("/checkingAccount/{registration}/{accountNumber}")
	public ResponseEntity<CheckingAccount> updateChecking(@PathVariable String registration,
			@PathVariable String accountNumber, @RequestBody @Valid CheckingAccount updateCa) throws Exception {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(manageService.updateByAccountNumberChecking(registration, accountNumber, updateCa));
	}

	@PutMapping("/specialAccount/{registration}/{accountNumber}")
	public ResponseEntity<SpecialAccount> updateSpecial(@PathVariable String registration,
			@PathVariable String accountNumber, @RequestBody SpecialAccount updateSa) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(manageService.updateByAccountNumberSpecial(registration, accountNumber, updateSa));
	}

	@PutMapping("/activateCard/{registration}/{accountNumber}")
	public ResponseEntity<Account> activateCard(@PathVariable String registration, @PathVariable String accountNumber,
			@RequestBody CreditCard creditCard) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(manageService.activateCard(registration, accountNumber, creditCard));
	}

	@DeleteMapping("/checkingAccount/{registration}/{accountNumber}")
	public ResponseEntity<Void> deleteChecking(@PathVariable String registration, @PathVariable String accountNumber) {
		manageService.deleteAccountChecking(registration, accountNumber);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/specialAccount/{registration}/{accountNumber}")
	public ResponseEntity<Void> deleteSpecial(@PathVariable String registration, @PathVariable String accountNumber) {
		manageService.deleteAccountSpecial(registration, accountNumber);
		return ResponseEntity.noContent().build();
	}

}