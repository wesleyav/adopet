package com.github.wesleyav.adopet.entities.dto.requests;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.github.wesleyav.adopet.entities.Adocao;
import com.github.wesleyav.adopet.entities.Animal;
import com.github.wesleyav.adopet.entities.Tutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdocaoRequestDTO {

	private UUID id;

	@NotNull(message = "O id do animal é obrigatório")
	private Integer animalId;

	@NotNull(message = "O id do tutor é obrigatório")
	private Integer tutorId;

	public Adocao toEntity() {
		Adocao adocao = new Adocao();

		Animal animal = new Animal();
		animal.setId(this.animalId);
		adocao.setAnimalId(animal);

		Tutor tutor = new Tutor();
		tutor.setId(this.tutorId);
		adocao.setTutorId(tutor);

		return adocao;
	}
}
