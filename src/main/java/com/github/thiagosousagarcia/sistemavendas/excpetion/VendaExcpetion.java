package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class VendaExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public VendaExcpetion (String message) {
		super(message);
	}
	
}
