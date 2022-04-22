package com.wipro.projetofinal.service.exeption;

public class AlreadExistException extends RuntimeException {

	public AlreadExistException() {
		super("Entidade com esses dados já existe!");
	}
}
