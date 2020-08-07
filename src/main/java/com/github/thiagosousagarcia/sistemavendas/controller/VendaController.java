package com.github.thiagosousagarcia.sistemavendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.VendaDTO;
import com.github.thiagosousagarcia.sistemavendas.model.Venda;
import com.github.thiagosousagarcia.sistemavendas.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private VendaService vendaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody VendaDTO vendaDTO){
		Venda novaVenda = this.vendaService.salvarVenda(vendaDTO);
		return novaVenda.getId();
	}
	
	
	
}
