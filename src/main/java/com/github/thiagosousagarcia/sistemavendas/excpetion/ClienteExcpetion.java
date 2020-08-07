package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class ClienteExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClienteExcpetion (String message) {
		super(message);
	}
}
