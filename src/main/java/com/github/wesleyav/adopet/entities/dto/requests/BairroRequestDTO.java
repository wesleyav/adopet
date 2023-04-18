package com.github.wesleyav.adopet.entities.dto.requests;

import com.github.wesleyav.adopet.entities.Bairro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BairroRequestDTO {

	private String nome;

	private CidadeRequestDTO cidade;

	public static Bairro toEntity(BairroRequestDTO bairroRequestDTO) {

		Bairro bairro = new Bairro();

		bairro.setNome(bairroRequestDTO.getNome());

		if (bairroRequestDTO.getCidade() != null) {
			bairro.setCidade(CidadeRequestDTO.toEntity(bairroRequestDTO.getCidade()));
		}

		return bairro;
	}

}
