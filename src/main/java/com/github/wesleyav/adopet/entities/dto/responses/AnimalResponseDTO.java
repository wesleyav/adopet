package com.github.wesleyav.adopet.entities.dto.responses;

import java.time.Instant;

import com.github.wesleyav.adopet.entities.Animal;

import lombok.Getter;

@Getter
public class AnimalResponseDTO {

	private Integer id;

	private String nome;

	private String idade;

	private String descricao;

	private Boolean adotado;

	private String imageUrl;

	private Instant createdAt;

	private Instant updatedAt;

	private AbrigoResponseDTO abrigo;

	public AnimalResponseDTO(Animal animal) {
		this.id = animal.getId();
		this.nome = animal.getNome();
		this.idade = animal.getIdade();
		this.descricao = animal.getDescricao();
		this.adotado = animal.getAdotado();
		this.imageUrl = animal.getImageUrl();
		this.createdAt = animal.getCreatedAt();
		this.updatedAt = animal.getUpdatedAt();
		
		if(animal.getAbrigo() != null) {
			this.abrigo = new AbrigoResponseDTO(animal.getAbrigo());			
		}
	}

}
