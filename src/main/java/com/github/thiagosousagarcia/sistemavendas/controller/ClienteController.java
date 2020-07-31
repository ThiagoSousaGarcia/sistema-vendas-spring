package com.github.thiagosousagarcia.sistemavendas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
		Cliente novoCliente = this.clienteService.save(cliente);
		return ResponseEntity.ok(novoCliente);
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
		Optional<Cliente> optCliente = this.clienteService.findById(id);
		
		if(optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
}
