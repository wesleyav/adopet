package com.github.wesleyav.adopet.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.wesleyav.adopet.entities.Abrigo;
import com.github.wesleyav.adopet.entities.dto.requests.AbrigoRequestDTO;
import com.github.wesleyav.adopet.entities.dto.responses.AbrigoResponseDTO;
import com.github.wesleyav.adopet.entities.dto.updates.AbrigoUpdateDTO;
import com.github.wesleyav.adopet.services.AbrigoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Abrigo")
public class AbrigoController {

	private AbrigoService abrigoService;

	public AbrigoController(AbrigoService abrigoService) {
		this.abrigoService = abrigoService;
	}

	@GetMapping(value = "/abrigos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar todos os abrigos")
	public ResponseEntity<List<AbrigoResponseDTO>> findAll() {
		List<Abrigo> abrigos = abrigoService.findAll();
		List<AbrigoResponseDTO> abrigoResponseDTOs = abrigos.stream().map(AbrigoResponseDTO::new)
				.collect(Collectors.toList());
		return new ResponseEntity<>(abrigoResponseDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/abrigos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar abrigo por id")
	public ResponseEntity<Abrigo> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(abrigoService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/abrigos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar um novo abrigo")
	public ResponseEntity<AbrigoResponseDTO> salvarAbrigoComEndereco(
			@RequestBody @Valid AbrigoRequestDTO abrigoRequestDTO) {
		Abrigo abrigo = abrigoRequestDTO.toEntity(abrigoRequestDTO);
		abrigo = abrigoService.save(abrigo);
		AbrigoResponseDTO abrigoResponseDTO = new AbrigoResponseDTO(abrigo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/abrigos/{id}")
				.buildAndExpand(abrigo.getId()).toUri();
		return ResponseEntity.created(uri).body(abrigoResponseDTO);
	}

	@DeleteMapping(value = "/abrigos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para remover abrigo por id")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
		abrigoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/abrigos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um abrigo")
	public ResponseEntity<AbrigoResponseDTO> update(@PathVariable Integer id,
			@RequestBody AbrigoUpdateDTO abrigoUpdateDTO) {

		Abrigo abrigo = abrigoUpdateDTO.toEntity(abrigoUpdateDTO);
		Abrigo abrigoUpdate = abrigoService.update(id, abrigo);

		AbrigoResponseDTO abrigoResponseDTO = new AbrigoResponseDTO(abrigoUpdate);

		return new ResponseEntity<>(abrigoResponseDTO, HttpStatus.OK);
	}

}
