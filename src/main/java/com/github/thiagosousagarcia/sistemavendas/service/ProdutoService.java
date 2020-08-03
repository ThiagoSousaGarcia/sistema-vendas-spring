package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.model.Produto;
import com.github.thiagosousagarcia.sistemavendas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto save(Produto produto) {
		return this.repository.save(produto);
	}
	
	public Page<Produto> findAll(Pageable pageable){
		return this.repository.findAll(pageable);
	}
	
	public Optional<Produto> findById(Long id){
		return this.repository.findById(id);
	}
	
	public Page<Produto> findByDescricaoContains(String descricao, Pageable pageable){
		return this.repository.findByDescricaoContains(descricao, pageable);
	}
	
}
