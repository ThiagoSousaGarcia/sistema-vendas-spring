package com.github.thiagosousagarcia.sistemavendas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.ClienteDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;
import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	
	@PostMapping
	public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO){
		Cliente novoCliente = this.clienteService.salvarCliente(clienteDTO.toEntity());
		ClienteDTO novoClienteDTO = novoCliente.toDTO();
		
		
		if(novoCliente != null) {
			return ResponseEntity.ok(novoClienteDTO);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}
	
	@GetMapping
	public Page<ClienteDTO> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Cliente> clientes = this.clienteService.findAll(pageable);
		
		return DTOConverter.toPage(clientes, ClienteDTO.class);
		
	}
	
	@GetMapping("/byNomeContains")
	public Page<ClienteDTO> findByNomeLike(@RequestParam(required = true) String nome, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Cliente> clientes = this.clienteService.findByNomeContains(nome, pageable);
		
		return DTOConverter.toPage(clientes, ClienteDTO.class);
		
	}
	
	
	@GetMapping("/byId")
	public ResponseEntity<ClienteDTO> getClienteById(@RequestParam(required = true) Long id){
		Optional<Cliente> optCliente = this.clienteService.findById(id);
		
		if(optCliente.isPresent()) {
			ClienteDTO clienteDTO = optCliente.get().toDTO();
			return ResponseEntity.ok(clienteDTO);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/byCpf")
	public ResponseEntity<ClienteDTO> getClienteByCpf(@RequestParam(required = true) String cpf){
		Optional<Cliente> optCliente = this.clienteService.findByCpf(cpf);
		
		if(optCliente.isPresent()) {
			ClienteDTO clienteDTO = optCliente.get().toDTO();
			return ResponseEntity.ok(clienteDTO);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
}
