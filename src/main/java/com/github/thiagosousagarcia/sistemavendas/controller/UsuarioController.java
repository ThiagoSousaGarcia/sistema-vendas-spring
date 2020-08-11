package com.github.thiagosousagarcia.sistemavendas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.UsuarioDTO;
import com.github.thiagosousagarcia.sistemavendas.model.Usuario;
import com.github.thiagosousagarcia.sistemavendas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> create (@RequestBody @Valid UsuarioDTO dto, final UriComponentsBuilder uriBuilder){
		Usuario novoUsuario = this.service.salvar(dto.toEntity());
		
		UsuarioDTO novoUsuarioDTO = novoUsuario.toDTO();
		final Long id = novoUsuarioDTO.getId();
		final URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(novoUsuarioDTO);
	}
	
}
