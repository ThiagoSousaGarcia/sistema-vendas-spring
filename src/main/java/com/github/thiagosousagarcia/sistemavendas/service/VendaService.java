package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.model.Venda;
import com.github.thiagosousagarcia.sistemavendas.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	
	public List<Venda> findByClienteCPF(String cpf){
		return this.vendaRepository.findByClienteCpf(cpf);
	}
}
