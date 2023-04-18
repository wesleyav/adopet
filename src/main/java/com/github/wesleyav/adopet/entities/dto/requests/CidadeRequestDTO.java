package com.github.wesleyav.adopet.entities.dto.requests;

import com.github.wesleyav.adopet.entities.Cidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CidadeRequestDTO {

	private String nome;

	private EstadoRequestDTO estado;

	public static Cidade toEntity(CidadeRequestDTO cidadeRequestDTO) {
		Cidade cidade = new Cidade();

		cidade.setNome(cidadeRequestDTO.getNome());
		if (cidadeRequestDTO.getEstado() != null) {
			cidade.setEstado(EstadoRequestDTO.toEntity(cidadeRequestDTO.getEstado()));
		}

		return cidade;
	}

}
