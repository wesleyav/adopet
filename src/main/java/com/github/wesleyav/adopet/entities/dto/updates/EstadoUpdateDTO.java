package com.github.wesleyav.adopet.entities.dto.updates;

import com.github.wesleyav.adopet.entities.Estado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoUpdateDTO {
	
	private Integer id;

	private String sigla;

	public static Estado toEntity(EstadoUpdateDTO estadoUpdateDTO) {
		Estado estado = new Estado();

		estado.setId(estadoUpdateDTO.getId());
		estado.setSigla(estadoUpdateDTO.getSigla());

		return estado;
	}
}
