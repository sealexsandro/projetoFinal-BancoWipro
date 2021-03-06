package com.wipro.projetofinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Moviment;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.entities.enums.MovimentDescription;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.exeption.InvalidValueException;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;

@Service
public class CustomerService {

	@Autowired
	private CheckingAccountRepository checkingAccountRepository;

	@Autowired
	private SpecialAccountRepository specialAccountRepository;

	public Account getAccount(String numberAccount) {
		Account account = checkingAccountRepository.findByAccountNumber(numberAccount);
		if (account != null) {
			return account;
		} else {
			account = specialAccountRepository.findByAccountNumber(numberAccount);
			Optional<Account> special = Optional.ofNullable(account);
			return special.orElseThrow(() -> new ResourceNotFoundExcception(numberAccount));
		}
	}

	public List<Moviment> getAllMovimenties(String accountNumber) {
		Account account = getAccount(accountNumber);
		return account.getMoviment();
	}

	public Account deposit(String numberAccount, Double value) throws Exception {
		Account account = getAccount(numberAccount);
		if (value > 0) {

			// depositando
			account.deposit(value);

			// Guardando a movimentação de deposito na conta
			Moviment moviment = new Moviment(value, account.getAccountNumber(), MovimentDescription.DEPOSITO);
			account.addMoviment(moviment);

			// Mandando o repository salvar
			account = saveAccount(account);

			return account;
		}
		throw new InvalidValueException(value);
	}

	// Metodo para Sacar
	public Account withdraw(String numberAccount, Double value) throws Exception {
		Account account = getAccount(numberAccount);
		if (value > 0) {

			if (account.getClass().getName().equals(CheckingAccount.class.getName())) {
				if (account.getBalance() >= value) {
					account.withdraw(value);

					// Guardando a movimentação de conta corrente
					Moviment moviment = new Moviment(value, account.getAccountNumber(), MovimentDescription.SAQUE);
					account.addMoviment(moviment);

					// Mandando o repository salvar
					account = saveAccount(account);

					return account;
				} else {
					throw new InvalidValueException("Saldo Insuficiente!");
				}
			} else {
				SpecialAccount specialAccount = (SpecialAccount) account;
				if ((specialAccount.getBalance() + specialAccount.getSpecialLimit()) >= value) {
					specialAccount.withdraw(value);

					// Guardando a movimentação de conta Especial
					Moviment moviment = new Moviment(value, account.getAccountNumber(), MovimentDescription.SAQUE);
					account.addMoviment(moviment);

					// Mandando o repository salvar
					account = saveAccount(account);

					return account;
				} else {
					throw new InvalidValueException("Saldo Insuficiente para Saque!");
				}
			}

		} else {
			throw new InvalidValueException(value);
		}

	}

	public Account transfer(String originAccount, String destinationAccount, Double value) throws Exception {

		Account originAcc = getAccount(originAccount);
		Account destinationAcc = getAccount(destinationAccount);

		if (value > 0) {

			if (originAcc.getBalance() >= value) {
				originAcc.withdraw(value);
				destinationAcc.deposit(value);

				// Guardando a movimentação na conta origem
				Moviment movimentOrigin = new Moviment(value, originAcc.getAccountNumber(), destinationAccount,
						MovimentDescription.TRANSFERENCIA_ENVIADA);
				originAcc.addMoviment(movimentOrigin);

				// Mandando o repository salvar as atualizaçoes da conta origem
				originAcc = saveAccount(originAcc);

				// Guardando a movimentação na conta Destino
				Moviment movimentDestination = new Moviment(value, originAcc.getAccountNumber(), destinationAccount,
						MovimentDescription.TRANSFERENCIA_RECEBIDA);
				destinationAcc.addMoviment(movimentDestination);

				// Mandando o repository salvar as atualizaçoes da conta destino
				destinationAcc = saveAccount(destinationAcc);

				return originAcc;
			} else {
				throw new InvalidValueException("Saldo Insuficiente para transferencia!");
			}

		} else {
			throw new InvalidValueException(value);
		}

	}

	private Account saveAccount(Account account) {
		if (account.getClass().getName().equals(CheckingAccount.class.getName())) {
			return checkingAccountRepository.save((CheckingAccount) account);
		} else {
			return specialAccountRepository.save((SpecialAccount) account);
		}
	}
}
