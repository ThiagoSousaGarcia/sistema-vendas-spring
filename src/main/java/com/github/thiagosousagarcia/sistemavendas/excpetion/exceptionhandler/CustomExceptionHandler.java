package com.github.thiagosousagarcia.sistemavendas.excpetion.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.thiagosousagarcia.sistemavendas.excpetion.ClienteCreationExcpetion;
import com.github.thiagosousagarcia.sistemavendas.excpetion.ClienteExcpetion;
import com.github.thiagosousagarcia.sistemavendas.excpetion.ProdutoException;
import com.github.thiagosousagarcia.sistemavendas.excpetion.VendaExcpetion;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(VendaExcpetion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handle(VendaExcpetion ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(ClienteCreationExcpetion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handle(ClienteCreationExcpetion ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(ClienteExcpetion.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handle(ClienteExcpetion ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
	@ExceptionHandler(ProdutoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handle(ProdutoException ex) {
		return new ErrorMessage(ex.getMessage());
	}
	
}
