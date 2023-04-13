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

import com.github.wesleyav.adopet.entities.Tutor;
import com.github.wesleyav.adopet.entities.dto.TutorRequestDTO;
import com.github.wesleyav.adopet.entities.dto.TutorResponseDTO;
import com.github.wesleyav.adopet.entities.dto.TutorUpdateDTO;
import com.github.wesleyav.adopet.services.TutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Tutor")
public class TutorController {

	private TutorService tutorService;

	public TutorController(TutorService tutorService) {
		this.tutorService = tutorService;
	}

	@GetMapping(value = "/tutores", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar todos os tutores")
	public ResponseEntity<List<TutorResponseDTO>> findAll() {
		List<Tutor> tutores = tutorService.findAll();
		List<TutorResponseDTO> responseDTOs = tutores.stream().map(TutorResponseDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/tutores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar tutor por id")
	public ResponseEntity<TutorResponseDTO> findById(@PathVariable Integer id) {
		Tutor tutor = tutorService.findById(id);
		TutorResponseDTO tutorResponseDTO = new TutorResponseDTO(tutor);
		return new ResponseEntity<>(tutorResponseDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/tutores", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar um novo tutor")
	public ResponseEntity<TutorResponseDTO> save(@RequestBody @Valid TutorRequestDTO tutorRequestDTO) {
		Tutor tutor = tutorRequestDTO.toEntity();
		Tutor tutorSalvo = tutorService.save(tutor);
		TutorResponseDTO tutorResponseDTO = new TutorResponseDTO(tutorSalvo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/tutores/{id}")
				.buildAndExpand(tutorSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(tutorResponseDTO);
	}

	@DeleteMapping(value = "/tutores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para remover tutor por id")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
		tutorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/tutores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um tutor")
	public ResponseEntity<TutorUpdateDTO> update(@PathVariable Integer id,
			@RequestBody TutorRequestDTO tutorRequestDTO) {
		Tutor tutor = tutorRequestDTO.toEntity();
		tutor.setId(id);
		Tutor tutorUpdate = tutorService.update(id, tutor);

		TutorUpdateDTO tutorUpdateDTO = new TutorUpdateDTO(tutorUpdate);

		return new ResponseEntity<>(tutorUpdateDTO, HttpStatus.OK);
	}
}
