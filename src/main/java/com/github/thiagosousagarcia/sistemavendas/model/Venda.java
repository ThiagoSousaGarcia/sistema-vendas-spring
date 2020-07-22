package com.github.thiagosousagarcia.sistemavendas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "VENDA")
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;
	
	@Column(name =  "DATA_VENDA")
	private LocalDate dataVenda;
	
	@Column(name = "VALOR_VENDA")
	private BigDecimal valorVenda;
}
