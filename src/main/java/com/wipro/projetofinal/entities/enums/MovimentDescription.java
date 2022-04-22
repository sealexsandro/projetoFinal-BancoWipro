package com.wipro.projetofinal.entities.enums;

public enum MovimentDescription {

	WITHDRAW(0),
	DEPOSIT(1),
	TRANSFER_SENT(2),
	TRANSFER_RECEIVED(3);


	private Integer code;

	private MovimentDescription(int code){
		this.code = code;
	}

	public Integer getCode(){
		return this.code;
	}
	public static MovimentDescription valueOf(int value){
		for (MovimentDescription code:
				MovimentDescription.values()) {
			if(code.getCode()==value) {return code;}
		}
		throw new IllegalArgumentException("Codigo invalido");
	}
}
