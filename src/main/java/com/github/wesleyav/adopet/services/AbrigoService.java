package com.github.wesleyav.adopet.services;

import java.time.Instant;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Abrigo;
import com.github.wesleyav.adopet.entities.Bairro;
import com.github.wesleyav.adopet.entities.Cidade;
import com.github.wesleyav.adopet.entities.Endereco;
import com.github.wesleyav.adopet.entities.Estado;
import com.github.wesleyav.adopet.repositories.AbrigoRepository;
import com.github.wesleyav.adopet.repositories.BairroRepository;
import com.github.wesleyav.adopet.repositories.EnderecoRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.adopet.services.exceptions.ResourceNotFoundException;

@Service
public class AbrigoService {

	private AbrigoRepository abrigoRepository;
	private EnderecoRepository enderecoRepository;
	private BairroRepository bairroRepository;

	public AbrigoService(AbrigoRepository abrigoRepository, EnderecoRepository enderecoRepository, BairroRepository bairroRepository) {
		this.abrigoRepository = abrigoRepository;
		this.enderecoRepository = enderecoRepository;
		this.bairroRepository = bairroRepository;
	}

	public List<Abrigo> findAll() {
		List<Abrigo> abrigos = abrigoRepository.findAll();

		if (abrigos.isEmpty()) {
			throw new ResourceEmptyException("Nenhum registro encontrado.");
		}

		return abrigos;
	}

	public Abrigo findById(Integer id) {
		return abrigoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Abrigo save(Abrigo abrigo) {
		Endereco endereco = abrigo.getEndereco();
		enderecoRepository.save(endereco);
		abrigo.setEndereco(endereco);
		abrigo.setCreatedAt(Instant.now());
		return abrigoRepository.save(abrigo);
	}

	public void deleteById(Integer id) {
		try {
			abrigoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public Abrigo update(Integer id, Abrigo obj) {
		try {
			Abrigo abrigoExistente = abrigoRepository.getReferenceById(id);

			if (abrigoExistente == null) {
				throw new ResourceEmptyException("Nenhum registro encontrado.");
			}

			Endereco enderecoExistente = abrigoExistente.getEndereco();
			Endereco enderecoAtualizado = obj.getEndereco();
			enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
			enderecoExistente.setNumero(enderecoAtualizado.getNumero());
			enderecoExistente.setCep(enderecoAtualizado.getCep());
			
			Bairro bairroExistente = bairroRepository.getReferenceById(id);
			bairroExistente.setNome(obj.getEndereco().getBairro().getNome());

			Cidade cidadeExistente = abrigoExistente.getEndereco().getBairro().getCidade();
			Cidade cidadeAtualizada = obj.getEndereco().getBairro().getCidade();
			cidadeExistente.setNome(cidadeAtualizada.getNome());

			Estado estadoExistente = abrigoExistente.getEndereco().getBairro().getCidade().getEstado();
			Estado estadoAtualizado = obj.getEndereco().getBairro().getCidade().getEstado();
			estadoExistente.setSigla(estadoAtualizado.getSigla());

			abrigoExistente.setNome(obj.getNome());
			abrigoExistente.setUpdatedAt(Instant.now());

			return abrigoRepository.save(abrigoExistente);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

}
