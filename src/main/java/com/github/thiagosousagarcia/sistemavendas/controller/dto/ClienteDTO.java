package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thiagosousagarcia.sistemavendas.model.Cliente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
	
	private Long id;
	
	@JsonProperty("Nome")
	private String nome;
	
	@JsonProperty("DataNascimento")
	private LocalDate dataNascimento;
	
	@JsonProperty("Cpf")
	private String cpf;
	
	@JsonProperty("Endereco")
	private String endereco;
	
	@JsonProperty("Bairro")
	private String bairro;
	
	@JsonProperty("Cidade")
	private String cidade;
	
	@JsonProperty("Complemento")
	private String complemento;
	
	@JsonProperty("Cep")
	private String cep;
	
	@JsonProperty("Telefone")
	private String telefone;
	
	@JsonProperty("Email")
	private String email;
	
	public Cliente toEntity() {
		final Cliente cliente = DTOConverter.toObject(this, Cliente.class);
		
		return cliente;
	}
}
