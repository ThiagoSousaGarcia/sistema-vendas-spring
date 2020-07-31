package com.github.thiagosousagarcia.sistemavendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
		Cliente novoCliente = this.clienteService.salvarCliente(cliente);
		
		if(novoCliente != null) {
			return ResponseEntity.ok(novoCliente);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> clientes = this.clienteService.findAll();
		
		return ResponseEntity.ok(clientes);
		
	}
	
	@GetMapping("/byNomeContains")
	@ResponseBody
	public ResponseEntity<List<Cliente>> findByNomeLike(@RequestParam(required = true) String nome){
		List<Cliente> clientes = this.clienteService.findByNomeContains(nome);
		
		return ResponseEntity.ok(clientes);
		
	}
	
	
	@GetMapping("/byId")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@RequestParam(required = true) Long id){
		Optional<Cliente> optCliente = this.clienteService.findById(id);
		
		if(optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/byCpf")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteByCpf(@RequestParam(required = true) String cpf){
		Optional<Cliente> optCliente = this.clienteService.findByCpf(cpf);
		
		if(optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
}
