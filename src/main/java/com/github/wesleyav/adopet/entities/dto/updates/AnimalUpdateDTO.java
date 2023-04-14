package com.github.wesleyav.adopet.entities.dto.updates;

import com.github.wesleyav.adopet.entities.Animal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalUpdateDTO {

	private String nome;

	private String idade;

	private String descricao;

	private Boolean adotado;

	private String imageUrl;

	private Integer abrigoId;

	public AnimalUpdateDTO(Animal animal) {
		this.nome = animal.getNome();
		this.idade = animal.getIdade();
		this.descricao = animal.getDescricao();
		this.adotado = animal.getAdotado();
		this.imageUrl = animal.getImageUrl();

//		Abrigo abrigo = new Abrigo();
//		abrigo.setId(animal.getAbrigo().getId());

		this.abrigoId = animal.getAbrigo().getId();
	}

}
