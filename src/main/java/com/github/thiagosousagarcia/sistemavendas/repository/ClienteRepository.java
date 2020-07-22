package com.github.thiagosousagarcia.sistemavendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.thiagosousagarcia.sistemavendas.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNomeLike(String nome);
	
	Cliente findByCpf(String cpf);
}
