package com.github.wesleyav.adopet.entities.dto.updates;

import com.github.wesleyav.adopet.entities.Bairro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BairroUpdateDTO {
	
	private Integer id;

	private String nome;

	private CidadeUpdateDTO cidade;

	public static Bairro toEntity(BairroUpdateDTO bairroUpdateDTO) {
		Bairro bairro = new Bairro();

		bairro.setId(bairroUpdateDTO.getId());
		bairro.setNome(bairroUpdateDTO.getNome());
		bairro.setCidade(CidadeUpdateDTO.toEntity(bairroUpdateDTO.getCidade()));

		return bairro;
	}

}
