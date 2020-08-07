package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.excpetion.ClienteCreationExcpetion;
import com.github.thiagosousagarcia.sistemavendas.excpetion.ClienteExcpetion;
import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.repository.ClienteRepository;
import com.github.thiagosousagarcia.sistemavendas.util.ValidaCpf;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	
	public Optional<Cliente> findById(Long id){
		return this.clienteRepository.findById(id);
	}
	
	public Optional<Cliente> findByCpf(String cpf){
		return this.findByCpf(cpf);
	}
	
	public Page<Cliente> findAll(Pageable pageable){
		return this.clienteRepository.findAll(pageable);
	}
	
	public Cliente salvarCliente(Cliente cliente) {
		if(!ValidaCpf.isCPF(cliente.getCpf())) {
			throw new ClienteCreationExcpetion("O CPF do cliente não é válido");
		}
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente encontrarClientePeloId(Long id) {
		Cliente cliente = this.findById(id)
										.orElseThrow(() -> 
												new ClienteExcpetion("Não foi encontrado nenhum cliente com esse ID: " +id));
		
		return cliente;
	}
	
	public Cliente encontrarClientePeloCpf(String cpf) {
		Cliente cliente = this.findByCpf(cpf)
											.orElseThrow(()-> 
												new ClienteExcpetion("Não foi encontrado nenhum cliente com esse CPF: " + cpf));
		
		return cliente;
	}
	
	public Page<Cliente> findByNomeContains(String nome, Pageable pageable){
		
		return this.clienteRepository.findByNomeContains(nome, pageable);
	}
	
	public void deletarClientePeloID(Long id) {
		Cliente cliente = encontrarClientePeloId(id);
		
		this.deleteCliente(cliente);
	}
	
	public void deleteCliente(Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}
	
	public Cliente updateCliente(Long id, Cliente clienteAtualizado) {
		Cliente cliente = this.encontrarClientePeloId(id);
		
		clienteAtualizado.setId(cliente.getId());
		return this.salvarCliente(clienteAtualizado);
	}
}
