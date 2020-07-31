package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente save(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> findById(Long id) {
		return this.clienteRepository.findById(id);
	}
	
	public Optional<Cliente> findByCpf(String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}
	
	public List<Cliente> findByNomeLike(String nome){
		
		return this.clienteRepository.findByNomeLike(nome);
	}
}
