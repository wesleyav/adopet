package com.github.wesleyav.adopet.entities.dto.responses;

import com.github.wesleyav.adopet.entities.Endereco;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EnderecoResponseDTO {

	private Integer id;

	private String logradouro;

	private String numero;

	private String cep;
	
	private BairroResponseDTO bairro;

	public EnderecoResponseDTO(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.cep = endereco.getCep();
		
		this.bairro = new BairroResponseDTO(endereco.getBairro());
	}

}
