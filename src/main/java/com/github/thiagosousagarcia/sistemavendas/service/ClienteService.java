package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente findByCpf(String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}
	
	public List<Cliente> findByNomeLike(String nome){
		
		return this.clienteRepository.findByNomeLike(nome);
	}
}
