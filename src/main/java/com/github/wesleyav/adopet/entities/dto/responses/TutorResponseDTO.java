package com.github.wesleyav.adopet.entities.dto.responses;

import java.time.Instant;

import com.github.wesleyav.adopet.entities.Tutor;

import lombok.Getter;

@Getter
public class TutorResponseDTO {

	private Integer id;

	private String nome;

	private String email;

	private String telefone;

	private String sobre;

	private String imageUrl;

	private String cidade;

	private Instant createdAt;

	private Instant updatedAt;

	public TutorResponseDTO(Tutor tutor) {
		this.id = tutor.getId();
		this.nome = tutor.getNome();
		this.email = tutor.getEmail();
		this.telefone = tutor.getTelefone();
		this.sobre = tutor.getSobre();
		this.imageUrl = tutor.getImageUrl();
		this.cidade = tutor.getCidade();
		this.createdAt = tutor.getCreatedAt();
		this.updatedAt = tutor.getUpdatedAt();
	}

}
