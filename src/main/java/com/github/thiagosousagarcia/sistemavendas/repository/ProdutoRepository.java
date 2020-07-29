package com.github.thiagosousagarcia.sistemavendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thiagosousagarcia.sistemavendas.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findByDescricaoLike(String descricao);
	
}
