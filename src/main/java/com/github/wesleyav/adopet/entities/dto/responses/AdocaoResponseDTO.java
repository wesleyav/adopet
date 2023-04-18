package com.github.wesleyav.adopet.entities.dto.responses;

import java.time.Instant;
import java.util.UUID;

import com.github.wesleyav.adopet.entities.Adocao;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AdocaoResponseDTO {

	private UUID id;

	private Integer animalId;

	private Integer tutorId;

	private Instant dataAdocao;

	public AdocaoResponseDTO(Adocao adocao) {
		this.id = adocao.getId();
		this.animalId = adocao.getAnimalId();
		this.tutorId = adocao.getTutorId();
		this.dataAdocao = adocao.getDataAdocao();
	}
}
