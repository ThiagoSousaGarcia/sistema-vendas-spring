package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thiagosousagarcia.sistemavendas.model.Produto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(of = {"id"})
public class ProdutoDTO {
	
	@JsonIgnore
	private Long id;
	
	@JsonProperty("Descricao")
	private String descricao;
	
	@JsonProperty("Preco")
	private BigDecimal preco;
	
	public Produto toEntity() {
		Produto produto = DTOConverter.toObject(this, Produto.class);
		
		return produto;
	}
	
}
