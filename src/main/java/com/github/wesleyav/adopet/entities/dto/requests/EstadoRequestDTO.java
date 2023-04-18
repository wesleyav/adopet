package com.github.wesleyav.adopet.entities.dto.requests;

import com.github.wesleyav.adopet.entities.Estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadoRequestDTO {

	private String sigla;

	public static Estado toEntity(EstadoRequestDTO estadoRequestDTO) {
		Estado estado = new Estado();

		estado.setSigla(estadoRequestDTO.getSigla());

		return estado;
	}

}
