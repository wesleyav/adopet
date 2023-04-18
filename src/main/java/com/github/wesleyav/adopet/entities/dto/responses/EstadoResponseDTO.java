package com.github.wesleyav.adopet.entities.dto.responses;

import com.github.wesleyav.adopet.entities.Estado;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EstadoResponseDTO {

	private Integer id;

	private String sigla;

	public EstadoResponseDTO(Estado estado) {
		this.id = estado.getId();
		this.sigla = estado.getSigla();
	}

}
