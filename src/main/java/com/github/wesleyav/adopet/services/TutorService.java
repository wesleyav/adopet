package com.github.wesleyav.adopet.services;

import java.time.Instant;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Tutor;
import com.github.wesleyav.adopet.repositories.TutorRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.adopet.services.exceptions.ResourceNotFoundException;

@Service
public class TutorService {

	private TutorRepository tutorRepository;

	public TutorService(TutorRepository tutorRepository) {
		this.tutorRepository = tutorRepository;
	}

	public List<Tutor> findAll() {

		List<Tutor> tutores = tutorRepository.findAll();

		if (tutores.isEmpty()) {
			throw new ResourceEmptyException("Nenhum registro encontrado.");
		}

		return tutores;
	}

	public Tutor findById(Integer id) {
		return tutorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Tutor save(Tutor tutor) {
		tutor.setCreatedAt(Instant.now());
		return tutorRepository.save(tutor);
	}

	public void deleteById(Integer id) {
		try {
			tutorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Tutor update(Integer id, Tutor obj) {
		try {
			Tutor tutor = tutorRepository.getReferenceById(id);

			tutor.setNome(obj.getNome());
			tutor.setTelefone(obj.getTelefone());
			tutor.setCidade(obj.getCidade());
			tutor.setSobre(obj.getSobre());
			tutor.setUpdatedAt((Instant.now()));
			tutor.setFoto(obj.getFoto());

			return tutorRepository.save(tutor);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
}
