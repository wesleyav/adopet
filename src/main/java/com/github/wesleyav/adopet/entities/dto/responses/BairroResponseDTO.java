package com.github.wesleyav.adopet.entities.dto.responses;

import com.github.wesleyav.adopet.entities.Bairro;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BairroResponseDTO {

	private Integer id;

	private String nome;

	private CidadeResponseDTO cidade;

	public BairroResponseDTO(Bairro bairro) {
		this.id = bairro.getId();
		this.nome = bairro.getNome();

		this.cidade = new CidadeResponseDTO(bairro.getCidade());
	}

}
