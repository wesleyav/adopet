package com.github.wesleyav.adopet.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Abrigo;
import com.github.wesleyav.adopet.entities.Animal;
import com.github.wesleyav.adopet.repositories.AbrigoRepository;
import com.github.wesleyav.adopet.repositories.AnimalRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.adopet.services.exceptions.ResourceNotFoundException;

@Service
public class AnimalService {

	private AnimalRepository animalRepository;
	private AbrigoRepository abrigoRepository;

	public AnimalService(AnimalRepository animalRepository, AbrigoRepository abrigoRepository) {
		this.animalRepository = animalRepository;
		this.abrigoRepository = abrigoRepository;
	}

	public List<Animal> findAllNotAdotados() {
		List<Animal> animaisNaoAdotados = animalRepository.findAllNaoAdotados();
		if (animaisNaoAdotados.isEmpty()) {
			throw new ResourceEmptyException("Nenhum registro encontrado.");
		}
		return animaisNaoAdotados;
	}

	public Animal findById(Integer id) {
		return animalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Animal save(Animal animal) {

		Optional<Abrigo> optAbrigo = abrigoRepository.findById(animal.getAbrigo().getId());

		if (optAbrigo.isPresent()) {
			Abrigo abrigo = animal.getAbrigo();
			abrigo.setCreatedAt(Instant.now());
			abrigoRepository.save(abrigo);
		} else {
			throw new ResourceEmptyException("Abrigo não encontrado.");
		}

		animal.setCreatedAt(Instant.now());
		return animalRepository.save(animal);
	}

	public void deleteById(Integer id) {
		try {
			animalRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Animal update(Integer id, Animal obj) {

		try {

			Optional<Abrigo> optAbrigo = abrigoRepository.findById(obj.getAbrigo().getId());

			if (optAbrigo.isPresent()) {
				Animal animalExistente = animalRepository.getReferenceById(id);

				if (animalExistente == null) {
					throw new ResourceEmptyException("Nenhum registro encontrado.");
				}

				animalExistente.setNome(obj.getNome());
				animalExistente.setIdade(obj.getIdade());
				animalExistente.setDescricao(obj.getDescricao());
				animalExistente.setAdotado(obj.getAdotado().booleanValue());
				animalExistente.setImageUrl(obj.getImageUrl());
				animalExistente.setUpdatedAt((Instant.now()));

				Abrigo abrigoId = abrigoRepository.getReferenceById(obj.getAbrigo().getId());

				animalExistente.setAbrigo(abrigoId);

				return animalRepository.save(animalExistente);

			} else {
				throw new ResourceEmptyException("Abrigo não encontrado.");
			}

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

}
