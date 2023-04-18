package com.github.wesleyav.adopet.entities.dto.responses;

import com.github.wesleyav.adopet.entities.Cidade;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CidadeResponseDTO {

	private Integer id;

	private String nome;

	private EstadoResponseDTO estado;

	public CidadeResponseDTO(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();

		this.estado = new EstadoResponseDTO(cidade.getEstado());
	}

}
