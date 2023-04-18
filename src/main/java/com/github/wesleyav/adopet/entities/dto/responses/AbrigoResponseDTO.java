package com.github.wesleyav.adopet.entities.dto.responses;

import java.time.Instant;

import com.github.wesleyav.adopet.entities.Abrigo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AbrigoResponseDTO {

	private Integer id;

	private String nome;

	private String email;

	private Instant createdAt;

	private Instant updatedAt;

	private EnderecoResponseDTO endereco;

	public AbrigoResponseDTO(Abrigo abrigo) {
		this.id = abrigo.getId();
		this.nome = abrigo.getNome();
		this.email = abrigo.getEmail();
		this.createdAt = abrigo.getCreatedAt();
		this.updatedAt = abrigo.getUpdatedAt();
		this.endereco = new EnderecoResponseDTO(abrigo.getEndereco());
	}

}
