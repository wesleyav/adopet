package com.github.wesleyav.adopet.entities.dto.updates;

import com.github.wesleyav.adopet.entities.Cidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeUpdateDTO {
	
	private Integer id;

	private String nome;

	private EstadoUpdateDTO estado;

	public static Cidade toEntity(CidadeUpdateDTO cidadeUpdateDTO) {
		Cidade cidade = new Cidade();

		cidade.setId(cidadeUpdateDTO.getId());
		cidade.setNome(cidadeUpdateDTO.getNome());
		cidade.setEstado(EstadoUpdateDTO.toEntity(cidadeUpdateDTO.getEstado()));

		return cidade;
	}

}
