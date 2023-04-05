package com.github.wesleyav.adopet.services;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Adocao;
import com.github.wesleyav.adopet.entities.Animal;
import com.github.wesleyav.adopet.entities.Tutor;
import com.github.wesleyav.adopet.repositories.AdocaoRepository;
import com.github.wesleyav.adopet.repositories.AnimalRepository;
import com.github.wesleyav.adopet.repositories.TutorRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceEmptyException;

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

	public List<Adocao> findAll() {
		List<Adocao> adocoes = adocaoRepository.findAll();

		if (adocoes.isEmpty()) {
			throw new ResourceEmptyException("Nenhum registro encontrado.");
		}

		return adocoes;
	}

	@Transactional
	public Adocao adotar(Adocao obj) {

		Adocao adocao = new Adocao();

		Animal animalExistente = animalRepository.getReferenceById(obj.getAnimalId());
		animalExistente.setAdotado(true);
		animalRepository.save(animalExistente);

		Tutor tutorExistente = tutorRepository.getReferenceById(obj.getTutorId());
		tutorRepository.save(tutorExistente);

		adocao.setAnimalId(obj.getAnimalId());
		adocao.setTutorId(obj.getTutorId());
		adocao.setDataAdocao(Instant.now());

		return adocaoRepository.save(adocao);

	}

}
