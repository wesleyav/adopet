package com.github.wesleyav.adopet.entities.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.github.wesleyav.adopet.entities.Abrigo;
import com.github.wesleyav.adopet.entities.Animal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnimalRequestDTO {

	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 3, max = 15, message = "O nome deverá ter no mínimo {min} caracteres e no máximo {max} caracteres")
	private String nome;

	@NotNull(message = "A idade é obrigatória")
	private String idade;

	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;

	private Boolean adotado;

	private String imageUrl;

	@NotNull(message = "O id do abrigo é obrigatório")
	private Integer abrigoId;

	public Animal toEntity() {
		Animal animal = new Animal();

		animal.setNome(this.nome);
		animal.setIdade(this.idade);
		animal.setDescricao(this.descricao);
		animal.setImageUrl(this.imageUrl);

		Abrigo abrigo = new Abrigo();
		abrigo.setId(this.abrigoId);
		animal.setAbrigo(abrigo);

		return animal;
	}

}
