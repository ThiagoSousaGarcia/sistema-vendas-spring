package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class ClienteCreationExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClienteCreationExcpetion (String message) {
		super(message);
	}
}
