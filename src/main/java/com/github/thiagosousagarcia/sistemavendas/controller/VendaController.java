package com.github.thiagosousagarcia.sistemavendas.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.DetalheVendaDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.VendaDTO;
import com.github.thiagosousagarcia.sistemavendas.model.Venda;
import com.github.thiagosousagarcia.sistemavendas.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private VendaService vendaService;
	
	@PostMapping
	public ResponseEntity<DetalheVendaDTO> create(@RequestBody VendaDTO vendaDTO,  final UriComponentsBuilder uriBuilder){
		Venda novaVenda = this.vendaService.salvarVenda(vendaDTO);
		DetalheVendaDTO dto = novaVenda.toDetalheVendaDTO();
		
		final Long id = dto.getId();
		final URI uri = uriBuilder.path("/vendas/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	
	
}
