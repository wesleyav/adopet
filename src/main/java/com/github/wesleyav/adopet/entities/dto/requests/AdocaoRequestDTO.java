package com.github.wesleyav.adopet.entities.dto.requests;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.github.wesleyav.adopet.entities.Adocao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdocaoRequestDTO {

	@NotBlank(message = "O UUID é obrigatório")
	private UUID id;

	@NotNull(message = "O id do animal é obrigatório")
	private Integer animalId;

	@NotNull(message = "O id do tutor é obrigatório")
	private Integer tutorId;

	private Instant dataAdocao;

	public static Adocao toEntity(AdocaoRequestDTO adocaoRequestDTO) {
		Adocao adocao = new Adocao();

		adocao.setId(adocaoRequestDTO.getId());
		adocao.setAnimalId(adocaoRequestDTO.getAnimalId());
		adocao.setTutorId(adocaoRequestDTO.getTutorId());
		adocao.setDataAdocao(adocaoRequestDTO.getDataAdocao());

		return adocao;
	}
}
