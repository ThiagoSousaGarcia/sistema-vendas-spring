package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.repository.ClienteRepository;
import com.github.thiagosousagarcia.sistemavendas.util.ValidaCpf;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Page<Cliente> findAll(Pageable pageable){
		return this.clienteRepository.findAll(pageable);
	}
	
	public Cliente salvarCliente(Cliente cliente) {
		if(ValidaCpf.isCPF(cliente.getCpf())) {
			return this.clienteRepository.save(cliente);
		}
		return null;
	}
	
	public Optional<Cliente> findById(Long id) {
		return this.clienteRepository.findById(id);
	}
	
	public Optional<Cliente> findByCpf(String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}
	
	public Page<Cliente> findByNomeContains(String nome, Pageable pageable){
		
		return this.clienteRepository.findByNomeContains(nome, pageable);
	}
}
