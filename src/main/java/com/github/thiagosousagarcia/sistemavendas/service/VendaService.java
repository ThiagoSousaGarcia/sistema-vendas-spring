package com.github.thiagosousagarcia.sistemavendas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.ItemVendaDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.VendaDTO;
import com.github.thiagosousagarcia.sistemavendas.excpetion.VendaExcpetion;
import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.model.ItemVenda;
import com.github.thiagosousagarcia.sistemavendas.model.Produto;
import com.github.thiagosousagarcia.sistemavendas.model.Venda;
import com.github.thiagosousagarcia.sistemavendas.repository.ItemVendaRepository;
import com.github.thiagosousagarcia.sistemavendas.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	public Page<Venda> findByClienteCPF(String cpf, Pageable pageable){
		return this.vendaRepository.findByClienteCpf(cpf, pageable);
	}
	
	/**A anotação @Transaction foi colocada pra garantir que a transação dê roolback em algum erro**/
	@Transactional
	public Venda salvarVenda(VendaDTO vendaDTO) {
		Venda venda = new Venda();
		
		Cliente cliente = clienteService.findById(vendaDTO.getCliente())
				.orElseThrow(() -> new VendaExcpetion("Não há nenhum cliente cadastrado com esse ID: " + vendaDTO.getCliente()));
		
		List<ItemVenda> itens = criarItens(venda, vendaDTO.getItens());
		
		venda.setCliente(cliente);
		venda.setDataVenda(vendaDTO.getDataVenda());
		venda.setItens(itens);
		venda.setValorVenda(calcularValorVenda(venda));
		
		venda = vendaRepository.save(venda);
		itens = itemVendaRepository.saveAll(itens);
		
		return venda;
		
	}
	
	private List<ItemVenda> criarItens (Venda venda, List<ItemVendaDTO> itensDTO){
		List<ItemVenda> itens = new ArrayList<ItemVenda>(); 
		
		if(itensDTO.isEmpty()) {
			throw new VendaExcpetion("Não há nenhum item de venda");
		}
		
		itensDTO.forEach( item -> {
			Produto produto = produtoService.findById(item.getProduto())
					.orElseThrow(() -> new VendaExcpetion("Não há nenhum produto com esse ID: " + item.getProduto()));
			
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setVenda(venda);
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(item.getQuantidade());
			itens.add(itemVenda);
		});
		
		return itens;
	}
	
	private BigDecimal calcularValorVenda(Venda venda) {
		BigDecimal valorVenda = BigDecimal.ZERO;
		
		for(ItemVenda item: venda.getItens()) {
			BigDecimal valorItemVenda = new BigDecimal(item.getQuantidade())
											.multiply(item.getProduto().getPreco());
			
			valorVenda = valorVenda.add(valorItemVenda);
		}
		
		return valorVenda;
	}
}
