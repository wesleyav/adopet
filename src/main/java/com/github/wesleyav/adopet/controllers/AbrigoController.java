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

import com.github.wesleyav.adopet.entities.Abrigo;
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
	public ResponseEntity<List<Abrigo>> findAll() {
		List<Abrigo> abrigos = abrigoService.findAll();
		return new ResponseEntity<>(abrigos, HttpStatus.OK);
	}

	@GetMapping(value = "/abrigos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar abrigo por id")
	public ResponseEntity<Abrigo> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(abrigoService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/abrigos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar um novo abrigo")
	public ResponseEntity<Abrigo> salvarAbrigoComEndereco(@RequestBody @Valid Abrigo abrigo) {
		abrigo = abrigoService.save(abrigo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/abrigos/{id}")
				.buildAndExpand(abrigo.getId()).toUri();
		return ResponseEntity.created(uri).body(abrigo);
	}

	@DeleteMapping(value = "/abrigos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para remover abrigo por id")
	public ResponseEntity<Abrigo> deleteById(@PathVariable("id") Integer id) {
		abrigoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/abrigos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um abrigo")
	public ResponseEntity<Abrigo> update(@PathVariable Integer id, @RequestBody Abrigo obj) {
		obj = abrigoService.update(id, obj);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

}
