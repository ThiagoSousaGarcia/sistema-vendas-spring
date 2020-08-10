package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class CreateClienteExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CreateClienteExcpetion (String message) {
		super(message);
	}
}
