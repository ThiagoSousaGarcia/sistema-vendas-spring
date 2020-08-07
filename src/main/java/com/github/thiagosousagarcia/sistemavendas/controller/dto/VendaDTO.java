package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(of = {"id"})
public class VendaDTO {
	
	private Long id;
	
	@JsonProperty ("Cliente")
	private Long cliente;
	
	@JsonProperty ("DataVenda")
	private LocalDate dataVenda;
	
	@JsonProperty("Itens")
	List<ItemVendaDTO> itens;
	
}
