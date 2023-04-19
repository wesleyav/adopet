package com.github.wesleyav.adopet.services;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Adocao;
import com.github.wesleyav.adopet.entities.Animal;
import com.github.wesleyav.adopet.entities.Tutor;
import com.github.wesleyav.adopet.repositories.AdocaoRepository;
import com.github.wesleyav.adopet.repositories.AnimalRepository;
import com.github.wesleyav.adopet.repositories.TutorRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.adopet.services.exceptions.ResourceNotFoundException;

@Service
public class AdocaoService {

	private AdocaoRepository adocaoRepository;
	private AnimalRepository animalRepository;
	private TutorRepository tutorRepository;

	public AdocaoService(AdocaoRepository adocaoRepository, AnimalRepository animalRepository,
			TutorRepository tutorRepository) {
		this.adocaoRepository = adocaoRepository;
		this.animalRepository = animalRepository;
		this.tutorRepository = tutorRepository;
	}

	public Page<Adocao> findAll(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Adocao> adocaoPage = adocaoRepository.findAll(pageable);

		if (adocaoPage.isEmpty()) {
			throw new ResourceEmptyException("Nenhum registro encontrado.");
		}

		return adocaoPage;
	}

	@Transactional
	public Adocao adotar(Adocao obj) {
		Adocao adocao = new Adocao();

		if (obj.getAnimalId() == null) {
			throw new ResourceEmptyException("O animalId é obrigatório");
		} else {
			Animal animalExistente = animalRepository.findById(obj.getAnimalId().getId())
					.orElseThrow(() -> new ResourceNotFoundException(obj.getAnimalId().getId()));

			if (animalExistente.getAdotado() == true) {
				throw new ResourceEmptyException("Animal já está adotado.");
			}

			animalExistente.setAdotado(true);
			adocao.setAnimalId(obj.getAnimalId());
		}

		if (obj.getTutorId() == null) {
			throw new ResourceEmptyException("O tutorId é obrigatório");
		} else {
			Tutor tutorExistente = tutorRepository.findById(obj.getTutorId().getId())
					.orElseThrow(() -> new ResourceNotFoundException(obj.getTutorId()));
			adocao.setTutorId(obj.getTutorId());
		}
		adocao.setDataAdocao(Instant.now());
		return adocaoRepository.save(adocao);
	}

	@Transactional
	public void cancelarAdocao(UUID id) {
		Adocao adocao = adocaoRepository.getReferenceById(id);

		Animal animalExistente = animalRepository.getReferenceById(adocao.getAnimalId().getId());
		animalExistente.setAdotado(false);

		adocaoRepository.deleteById(adocao.getId());
	}
}
