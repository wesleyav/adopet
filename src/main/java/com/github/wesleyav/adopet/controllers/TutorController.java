package com.github.wesleyav.adopet.controllers;

import java.net.URI;
import java.util.List;

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
	public ResponseEntity<List<Tutor>> findAll() {
		List<Tutor> tutores = tutorService.findAll();
		return new ResponseEntity<>(tutores, HttpStatus.OK);
	}

	@GetMapping(value = "/tutores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar tutor por id")
	public ResponseEntity<Tutor> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(tutorService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/tutores", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar um novo tutor")
	public ResponseEntity<Tutor> save(@RequestBody @Valid Tutor tutor) {
		tutor = tutorService.save(tutor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/tutores/{id}")
				.buildAndExpand(tutor.getId()).toUri();
		return ResponseEntity.created(uri).body(tutor);
	}

	@DeleteMapping(value = "/tutores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para remover tutor por id")
	public ResponseEntity<Tutor> deleteById(@PathVariable("id") Integer id) {
		tutorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/tutores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um tutor")
	public ResponseEntity<Tutor> update(@PathVariable Integer id, @RequestBody Tutor obj) {
		obj = tutorService.update(id, obj);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
}
