package com.github.wesleyav.adopet.entities;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.github.wesleyav.adopet.entities.dto.requests.AdocaoRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString(of = { "id", "nome", "email", "telefone", "cidade", "sobre", "imageUrl" })
@Entity
@Table(name = "adocao")
public class Adocao {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutorId;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animalId;

	private Instant dataAdocao;

	public Adocao(AdocaoRequestDTO adocaoRequestDTO, Animal animal, Tutor tutor) {
		this.tutorId = tutor;
		this.animalId = animal;

	}

}
