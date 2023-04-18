package com.github.wesleyav.adopet.entities.dto.updates;

import com.github.wesleyav.adopet.entities.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoUpdateDTO {
	
	private Integer id;

	private String logradouro;

	private String numero;

	private String cep;

	private BairroUpdateDTO bairro;

	public static Endereco toEntity(EnderecoUpdateDTO enderecoUpdateDTO) {
		Endereco endereco = new Endereco();

		endereco.setId(enderecoUpdateDTO.getId());
		endereco.setLogradouro(enderecoUpdateDTO.getLogradouro());
		endereco.setNumero(enderecoUpdateDTO.getNumero());
		endereco.setCep(enderecoUpdateDTO.getCep());
		endereco.setBairro(BairroUpdateDTO.toEntity(enderecoUpdateDTO.getBairro()));

		return endereco;
	}

}
