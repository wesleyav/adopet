package com.github.wesleyav.adopet.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.wesleyav.adopet.entities.Adocao;
import com.github.wesleyav.adopet.services.AdocaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Adoção")
public class AdocaoController {

	private AdocaoService adocaoService;

	public AdocaoController(AdocaoService adocaoService) {
		this.adocaoService = adocaoService;
	}

	@GetMapping(value = "/adocoes", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar todos as adoções")
	public ResponseEntity<List<Adocao>> findAll() {
		List<Adocao> adocoes = adocaoService.findAll();
		return new ResponseEntity<>(adocoes, HttpStatus.OK);
	}

	@PostMapping(value = "/adocoes", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar uma nova adoção")
	public ResponseEntity<Adocao> adotar(@RequestBody Adocao adocao) {
		adocao = adocaoService.adotar(adocao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/adocoes/{id}")
				.buildAndExpand(adocao.getId()).toUri();

		return ResponseEntity.created(uri).body(adocao);

	}

}
