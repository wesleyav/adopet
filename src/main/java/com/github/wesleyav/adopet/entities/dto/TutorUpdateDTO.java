package com.github.wesleyav.adopet.entities.dto;

import com.github.wesleyav.adopet.entities.Tutor;

import lombok.Getter;

@Getter
public class TutorUpdateDTO {

	private String nome;

	private String email;

	private String telefone;

	private String sobre;

	private String imageUrl;

	private String cidade;

	public TutorUpdateDTO(Tutor tutor) {
		this.nome = tutor.getNome();
		this.email = tutor.getEmail();
		this.telefone = tutor.getTelefone();
		this.sobre = tutor.getSobre();
		this.imageUrl = tutor.getImageUrl();
		this.cidade = tutor.getCidade();
	}

}
