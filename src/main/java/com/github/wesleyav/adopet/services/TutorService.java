package com.github.wesleyav.adopet.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Tutor;
import com.github.wesleyav.adopet.repositories.TutorRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceNotFoundException;

@Service
public class TutorService {

	private TutorRepository tutorRepository;

	public TutorService(TutorRepository tutorRepository) {
		this.tutorRepository = tutorRepository;
	}

	public List<Tutor> findAll() {
		return tutorRepository.findAll();
	}

	public Tutor findById(Integer id) {
		return tutorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Tutor save(Tutor tutor) {
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
			tutor.setFoto(obj.getFoto());

			return tutorRepository.save(tutor);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
}
