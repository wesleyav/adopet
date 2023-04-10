package com.github.wesleyav.adopet.services;

import java.time.Instant;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Abrigo;
import com.github.wesleyav.adopet.entities.Animal;
import com.github.wesleyav.adopet.entities.Bairro;
import com.github.wesleyav.adopet.entities.Cidade;
import com.github.wesleyav.adopet.entities.Endereco;
import com.github.wesleyav.adopet.entities.Estado;
import com.github.wesleyav.adopet.repositories.AbrigoRepository;
import com.github.wesleyav.adopet.repositories.AnimalRepository;
import com.github.wesleyav.adopet.repositories.BairroRepository;
import com.github.wesleyav.adopet.repositories.CidadeRepository;
import com.github.wesleyav.adopet.repositories.EnderecoRepository;
import com.github.wesleyav.adopet.repositories.EstadoRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.adopet.services.exceptions.ResourceNotFoundException;

@Service
public class AnimalService {

	private AnimalRepository animalRepository;
	private AbrigoRepository abrigoRepository;
	private EnderecoRepository enderecoRepository;
	private BairroRepository bairroRepository;
	private CidadeRepository cidadeRepository;
	private EstadoRepository estadoRepository;

	public AnimalService(AnimalRepository animalRepository, AbrigoRepository abrigoRepository,
			EnderecoRepository enderecoRepository, BairroRepository bairroRepository, CidadeRepository cidadeRepository,
			EstadoRepository estadoRepository) {
		this.animalRepository = animalRepository;
		this.abrigoRepository = abrigoRepository;
		this.enderecoRepository = enderecoRepository;
		this.bairroRepository = bairroRepository;
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
	}

	public List<Animal> findAll() {
		List<Animal> animais = animalRepository.findAll();

		if (animais.isEmpty()) {
			throw new ResourceEmptyException("Nenhum registro encontrado.");
		}

		return animais;
	}

	public Animal findById(Integer id) {
		return animalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Animal save(Animal animal) {
		Abrigo abrigo = animal.getAbrigo();
		abrigo.setCreatedAt(Instant.now());
		abrigoRepository.save(abrigo);

		animal.setCreatedAt(Instant.now());
		return animalRepository.save(animal);
	}

	public void deleteById(Integer id) {
		try {
			animalRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public Animal update(Integer id, Animal obj) {
		try {
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

			Abrigo abrigoExistente = abrigoRepository.getReferenceById(id);
			abrigoExistente.setNome(obj.getAbrigo().getNome());
			abrigoExistente.setUpdatedAt(Instant.now());

			Endereco enderecoExistente = enderecoRepository.getReferenceById(id);
			enderecoExistente.setLogradouro(obj.getAbrigo().getEndereco().getLogradouro());
			enderecoExistente.setNumero(obj.getAbrigo().getEndereco().getNumero());
			enderecoExistente.setCep(obj.getAbrigo().getEndereco().getCep());

			Bairro bairroExistente = bairroRepository.getReferenceById(id);
			bairroExistente.setNome(obj.getAbrigo().getEndereco().getBairro().getNome());

			Cidade cidadeExistente = cidadeRepository.getReferenceById(id);
			cidadeExistente.setNome(obj.getAbrigo().getEndereco().getBairro().getCidade().getNome());

			Estado estadoExistente = estadoRepository.getReferenceById(id);
			estadoExistente.setSigla(obj.getAbrigo().getEndereco().getBairro().getCidade().getEstado().getSigla());

			return animalRepository.save(animalExistente);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

}
