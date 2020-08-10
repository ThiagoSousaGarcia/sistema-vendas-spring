package com.github.thiagosousagarcia.sistemavendas.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.DetalheVendaDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.VendaDTO;
import com.github.thiagosousagarcia.sistemavendas.model.Venda;
import com.github.thiagosousagarcia.sistemavendas.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private VendaService vendaService;
	
	@PostMapping
	public ResponseEntity<DetalheVendaDTO> create(@RequestBody @Valid VendaDTO vendaDTO,  final UriComponentsBuilder uriBuilder){
		Venda novaVenda = this.vendaService.salvarVenda(vendaDTO);
		DetalheVendaDTO dto = novaVenda.toDetalheVendaDTO();
		
		final Long id = dto.getId();
		final URI uri = uriBuilder.path("/vendas/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	@GetMapping
	public Page<DetalheVendaDTO> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Venda> vendas = this.vendaService.findAll(pageable);
		List<DetalheVendaDTO> detalhesVendas = new ArrayList<>();
		
		if(vendas != null) {
			detalhesVendas = vendas.getContent().stream().map(Venda::toDetalheVendaDTO).collect(Collectors.toList());
		}
		
		return new PageImpl<DetalheVendaDTO>(detalhesVendas, pageable, detalhesVendas.size());
	}
	
	@GetMapping("/byClienteCpf")
	public Page<DetalheVendaDTO> findByClienteCpf(@RequestParam String cpf, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Venda> vendas = this.vendaService.findByClienteCPF(cpf, pageable);
		List<DetalheVendaDTO> detalhesVendas = new ArrayList<>();
		
		if(vendas != null) {
			detalhesVendas = vendas.getContent().stream().map(Venda::toDetalheVendaDTO).collect(Collectors.toList());
		}
		
		return new PageImpl<DetalheVendaDTO>(detalhesVendas, pageable, detalhesVendas.size());
	}
	
	@GetMapping("/byId")
	public ResponseEntity<DetalheVendaDTO> getVendaById(@RequestParam(required = true) Long id){
		Venda venda = this.vendaService.encontrarVendaPeloId(id);
		DetalheVendaDTO dto = venda.toDetalheVendaDTO();
		
		return ResponseEntity.ok(dto);
	}
	
	@PatchMapping("/status")
	public ResponseEntity<String> cancelVenda(@RequestParam(required = true) Long id){
		Venda venda = this.vendaService.cancelarVenda(id);
				
		return ResponseEntity.ok("A venda com código " + venda.getId() + " foi cancelada com sucesso!");
	}
	
	
	
	
	
	
}
