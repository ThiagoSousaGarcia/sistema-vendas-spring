package com.github.thiagosousagarcia.sistemavendas.excpetion;

public class ProdutoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProdutoException (String message) {
		super(message);
	}
}
