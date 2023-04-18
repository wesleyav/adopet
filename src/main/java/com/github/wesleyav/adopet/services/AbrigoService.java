package com.github.wesleyav.adopet.services;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.wesleyav.adopet.entities.Abrigo;
import com.github.wesleyav.adopet.entities.Bairro;
import com.github.wesleyav.adopet.entities.Cidade;
import com.github.wesleyav.adopet.entities.Endereco;
import com.github.wesleyav.adopet.entities.Estado;
import com.github.wesleyav.adopet.entities.dto.responses.AbrigoResponseDTO;
import com.github.wesleyav.adopet.repositories.AbrigoRepository;
import com.github.wesleyav.adopet.repositories.BairroRepository;
import com.github.wesleyav.adopet.repositories.CidadeRepository;
import com.github.wesleyav.adopet.repositories.EnderecoRepository;
import com.github.wesleyav.adopet.repositories.EstadoRepository;
import com.github.wesleyav.adopet.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.adopet.services.exceptions.ResourceNotFoundException;

@Service
public class AbrigoService {

	private AbrigoRepository abrigoRepository;
	private EnderecoRepository enderecoRepository;
	private BairroRepository bairroRepository;
	private CidadeRepository cidadeRepository;
	private EstadoRepository estadoRepository;

	public AbrigoService(AbrigoRepository abrigoRepository, EnderecoRepository enderecoRepository,
			BairroRepository bairroRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
		this.abrigoRepository = abrigoRepository;
		this.enderecoRepository = enderecoRepository;
		this.bairroRepository = bairroRepository;
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
	}

	public Page<AbrigoResponseDTO> findAll(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Abrigo> abrigosPage = abrigoRepository.findAll(pageable);

		if (abrigosPage.isEmpty()) {
			throw new ResourceEmptyException("Nenhum registro encontrado.");
		}

		return abrigosPage.map(AbrigoResponseDTO::new);
	}

	public Abrigo findById(Integer id) {
		return abrigoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}


	@Transactional
	public Abrigo save(Abrigo abrigo) {

		if (abrigo.getEndereco().getBairro() == null) {
			throw new ResourceEmptyException("Bairro obrigatório.");
		}

		if (abrigo.getEndereco().getBairro().getCidade() == null) {
			throw new ResourceEmptyException("Cidade obrigatório.");
		}

		if (abrigo.getEndereco().getBairro().getCidade().getEstado() == null) {
			throw new ResourceEmptyException("Estado obrigatório.");
		}

		Endereco endereco = abrigo.getEndereco();
		enderecoRepository.save(endereco);

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

		if (obj.getEndereco() == null) {
			throw new ResourceEmptyException("Endereço obrigatório.");
		}
		if (obj.getEndereco().getId() == null) {
			throw new ResourceEmptyException("id nullo.");
		}

		if (obj.getEndereco().getBairro() == null) {
			throw new ResourceEmptyException("Bairro obrigatório.");
		}

		if (obj.getEndereco().getBairro().getCidade() == null) {
			throw new ResourceEmptyException("Cidade obrigatório.");
		}

		if (obj.getEndereco().getBairro().getCidade().getEstado() == null) {
			throw new ResourceEmptyException("Estado obrigatório.");
		}

		try {
			Abrigo abrigoExistente = abrigoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

			if (abrigoExistente == null) {
				throw new ResourceEmptyException("asdf.");
			}

			abrigoExistente.setNome(obj.getNome());
			abrigoExistente.setEmail(obj.getEmail());

			Integer enderecoId = obj.getEndereco().getId();
			Endereco endereco = enderecoRepository.findById(enderecoId)
					.orElseThrow(() -> new ResourceNotFoundException(enderecoId));
			endereco.setId(enderecoId);
			endereco.setLogradouro(obj.getEndereco().getLogradouro());
			endereco.setNumero(obj.getEndereco().getNumero());
			endereco.setCep(obj.getEndereco().getCep());

			Integer bairroId = obj.getEndereco().getBairro().getId();
			Bairro bairro = bairroRepository.findById(bairroId)
					.orElseThrow(() -> new ResourceNotFoundException(bairroId));
			bairro.setId(bairroId);
			bairro.setNome(obj.getEndereco().getBairro().getNome());

			Integer cidadeId = obj.getEndereco().getBairro().getCidade().getId();
			Cidade cidade = cidadeRepository.findById(cidadeId)
					.orElseThrow(() -> new ResourceNotFoundException(cidadeId));
			cidade.setId(cidadeId);
			cidade.setNome(obj.getEndereco().getBairro().getCidade().getNome());

			Integer estadoId = obj.getEndereco().getBairro().getCidade().getEstado().getId();
			Estado estado = estadoRepository.findById(estadoId)
					.orElseThrow(() -> new ResourceNotFoundException(estadoId));
			estado.setId(estadoId);
			estado.setSigla(obj.getEndereco().getBairro().getCidade().getEstado().getSigla());

			cidade.setEstado(estado);
			bairro.setCidade(cidade);
			endereco.setBairro(bairro);
			abrigoExistente.setEndereco(endereco);

			return abrigoRepository.save(abrigoExistente);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

}
