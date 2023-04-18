package com.github.wesleyav.adopet.entities.dto.updates;

import com.github.wesleyav.adopet.entities.Abrigo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbrigoUpdateDTO {

	private String nome;

	private String email;

	private EnderecoUpdateDTO endereco;

	public static Abrigo toEntity(AbrigoUpdateDTO abrigoUpdateDTO) {

		if (abrigoUpdateDTO == null) {
			return null;
		}

		Abrigo abrigo = new Abrigo();

		abrigo.setNome(abrigoUpdateDTO.getNome());
		abrigo.setEmail(abrigoUpdateDTO.getEmail());
		abrigo.setEndereco(EnderecoUpdateDTO.toEntity(abrigoUpdateDTO.getEndereco()));

		return abrigo;
	}

}
