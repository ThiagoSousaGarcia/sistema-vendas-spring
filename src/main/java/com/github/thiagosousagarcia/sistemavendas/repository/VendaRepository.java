package com.github.thiagosousagarcia.sistemavendas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thiagosousagarcia.sistemavendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	Page<Venda> findByClienteCpf (String cpf, Pageable pageable);
	
}
