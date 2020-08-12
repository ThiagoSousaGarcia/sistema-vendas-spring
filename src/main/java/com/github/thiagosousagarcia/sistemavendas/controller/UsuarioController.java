package com.github.thiagosousagarcia.sistemavendas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.CredenciaisDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.TokenDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.UsuarioDTO;
import com.github.thiagosousagarcia.sistemavendas.excpetion.InvalidPasswordException;
import com.github.thiagosousagarcia.sistemavendas.model.Usuario;
import com.github.thiagosousagarcia.sistemavendas.security.jwt.JwtService;
import com.github.thiagosousagarcia.sistemavendas.security.service.UserService;
import com.github.thiagosousagarcia.sistemavendas.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@ApiOperation("Cadastra um novo usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado com sucesso")
	})
	@PostMapping
	public ResponseEntity<UsuarioDTO> create (@RequestBody @Valid UsuarioDTO usuarioDTO, final UriComponentsBuilder uriBuilder){
		Usuario novoUsuario = this.service.salvar(usuarioDTO.toEntity());
		
		UsuarioDTO novoUsuarioDTO = novoUsuario.toDTO();
		final Long id = novoUsuarioDTO.getId();
		final URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(novoUsuarioDTO);
	}
	
	@ApiOperation("Autentica um usuário")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário autenticado com sucesso")
	})
	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> autenticate(@RequestBody CredenciaisDTO credenciaisDTO) {
		try {
			Usuario usuario = Usuario.builder()
					.login(credenciaisDTO.getLogin())
					.senha(credenciaisDTO.getSenha()).build();
			userService.autenticate(usuario);
			String token = jwtService.generateToken(usuario);
			TokenDTO tokenDTO = new TokenDTO(usuario.getLogin(), token);
			
			return ResponseEntity.ok(tokenDTO); 
		}catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
	
}
