package com.github.wesleyav.adopet.entities.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.github.wesleyav.adopet.entities.Abrigo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AbrigoRequestDTO {

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	private String email;

	@NotNull(message = "O endereco é obrigatório")
	private EnderecoRequestDTO endereco;

	public static Abrigo toEntity(AbrigoRequestDTO abrigoRequestDTO) {
		Abrigo abrigo = new Abrigo();
		abrigo.setNome(abrigoRequestDTO.getNome());
		abrigo.setEmail(abrigoRequestDTO.getEmail());
		abrigo.setEndereco(EnderecoRequestDTO.toEntity(abrigoRequestDTO.getEndereco()));

		return abrigo;
	}

}
