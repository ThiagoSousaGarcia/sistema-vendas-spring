package com.github.thiagosousagarcia.sistemavendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thiagosousagarcia.sistemavendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	List<Venda> findByClienteCpf (String cpf);
	
}
