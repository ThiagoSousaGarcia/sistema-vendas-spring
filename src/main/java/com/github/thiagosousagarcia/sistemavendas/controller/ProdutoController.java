package com.github.thiagosousagarcia.sistemavendas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.ProdutoDTO;
import com.github.thiagosousagarcia.sistemavendas.model.Produto;
import com.github.thiagosousagarcia.sistemavendas.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService service;
	
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid ProdutoDTO produtoDTO, final UriComponentsBuilder uriBuilder){
		Produto novoProduto = this.service.save(produtoDTO.toEntity());
		ProdutoDTO novoProdutoDTO = novoProduto.toDTO();
		final Long id = novoProdutoDTO.getId();
		final URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(novoProdutoDTO);
	}
	
	@GetMapping
	public Page<ProdutoDTO> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Produto> produtos = this.service.findAll(pageable);
		
		return DTOConverter.toPage(produtos, ProdutoDTO.class);
	}
	
	@GetMapping("byId")
	public ResponseEntity<ProdutoDTO> findById(@RequestParam(required = true) Long id){
		Produto produto = this.service.encontrarProdutoPeloID(id);

		ProdutoDTO produtoDTO = produto.toDTO();
		return ResponseEntity.ok(produtoDTO);
	}
	
	@GetMapping("byDescricaoContains")
	public Page<ProdutoDTO> findByDescricaoContains(@RequestParam(required = true) String descricao, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Produto> produtos = this.service.findByDescricaoContains(descricao, pageable);
		
		return DTOConverter.toPage(produtos, ProdutoDTO.class);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO){
		Produto produtoAtualizado = this.service.updateProduto(id, produtoDTO.toEntity());
		
		return ResponseEntity.ok(produtoAtualizado.toDTO());
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteProduto(@PathVariable Long id) {
		this.service.deletarProdutoPeloID(id);
		return ResponseEntity.noContent().build();	
	}
}
