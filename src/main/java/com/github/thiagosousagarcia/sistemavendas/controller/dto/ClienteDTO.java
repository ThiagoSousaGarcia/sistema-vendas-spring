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
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("data_nascimento")
	private LocalDate data_nascimento;
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("endereco")
	private String endereco;
	
	@JsonProperty("bairro")
	private String bairro;
	
	@JsonProperty("cidade")
	private String cidade;
	
	@JsonProperty("complemento")
	private String complemento;
	
	@JsonProperty("cep")
	private String cep;
	
	@JsonProperty("telefone")
	private String telefone;
	
	@JsonProperty("email")
	private String email;
	
	public Cliente toEntity() {
		final Cliente cliente = DTOConverter.toObject(this, Cliente.class);
		
		return cliente;
	}
}
