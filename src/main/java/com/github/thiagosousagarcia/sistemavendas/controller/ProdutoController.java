package com.github.thiagosousagarcia.sistemavendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.ProdutoDTO;
import com.github.thiagosousagarcia.sistemavendas.model.Produto;
import com.github.thiagosousagarcia.sistemavendas.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService service;
	
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO produtoDTO){
		Produto novoProduto = this.service.save(produtoDTO.toEntity());
		ProdutoDTO novoProdutoDTO = novoProduto.toDTO();
		
		return ResponseEntity.ok(novoProdutoDTO);
	}
}
