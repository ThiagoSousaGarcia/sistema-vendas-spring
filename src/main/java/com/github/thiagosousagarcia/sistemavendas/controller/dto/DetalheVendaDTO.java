package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(of = {"id"})
public class DetalheVendaDTO {

	private Long id;
	
	@JsonProperty("Cliente")
	private ClienteDTO cliente;
	
	@JsonProperty("DataVenda")
	private LocalDate dataVenda;
	
	@JsonProperty("ValorVenda")
	private BigDecimal valorVenda;
	
	@JsonProperty("itens")
	List<DetalheItemVendaDTO> itens;
	

}
