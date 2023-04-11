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

import com.github.wesleyav.adopet.entities.Animal;
import com.github.wesleyav.adopet.services.AnimalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Animal")
public class AnimalController {

	private AnimalService animalService;

	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	@GetMapping(value = "/animais", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar todos os animais não adotados")
	public ResponseEntity<List<Animal>> findAllNotAdotados() {
		List<Animal> animaisNaoAdotados = animalService.findAllNotAdotados();
		return new ResponseEntity<>(animaisNaoAdotados, HttpStatus.OK);
	}

	@GetMapping(value = "/animais/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar animal por id")
	public ResponseEntity<Animal> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(animalService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/animais", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar um novo animal")
	public ResponseEntity<Animal> save(@RequestBody @Valid Animal animal) {
		animal = animalService.save(animal);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/animais/{id}")
				.buildAndExpand(animal.getId()).toUri();
		return ResponseEntity.created(uri).body(animal);
	}

	@DeleteMapping(value = "/animais/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para remover animal por id")
	public ResponseEntity<Animal> deleteById(@PathVariable("id") Integer id) {
		animalService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/animais/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um animal")
	public ResponseEntity<Animal> update(@PathVariable Integer id, @RequestBody Animal obj) {
		obj = animalService.update(id, obj);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

}
