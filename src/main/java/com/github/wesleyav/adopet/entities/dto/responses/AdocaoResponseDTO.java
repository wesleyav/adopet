package com.github.wesleyav.adopet.entities.dto.responses;

import java.time.Instant;
import java.util.UUID;

import com.github.wesleyav.adopet.entities.Adocao;

import lombok.Getter;

@Getter
public class AdocaoResponseDTO {

	private UUID id;

	private Integer tutorId;

	private Integer animalId;

	private Instant dataAdocao;

	public AdocaoResponseDTO(Adocao adocao) {
		this.id = adocao.getId();

		if (adocao.getAnimalId() != null) {
			this.tutorId = adocao.getTutorId().getId();
		}
		if (adocao.getTutorId() != null) {
			this.animalId = adocao.getAnimalId().getId();
		}

		this.dataAdocao = adocao.getDataAdocao();
	}

}
