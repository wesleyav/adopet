package com.github.wesleyav.adopet.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.wesleyav.adopet.entities.Adocao;
import com.github.wesleyav.adopet.entities.dto.requests.AdocaoRequestDTO;
import com.github.wesleyav.adopet.entities.dto.responses.AdocaoResponseDTO;
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
	@Operation(summary = "Endpoint para listar todas as adoções")
	public ResponseEntity<Page<AdocaoResponseDTO>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<AdocaoResponseDTO> adocoes = adocaoService.findAll(pageNumber, pageSize);

		return new ResponseEntity<>(adocoes, HttpStatus.OK);
	}

	@PostMapping(value = "/adocoes", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar uma nova adoção")
	public ResponseEntity<AdocaoResponseDTO> adotar(@RequestBody AdocaoRequestDTO adocaoRequestDTO) {
		Adocao adocao = adocaoRequestDTO.toEntity(adocaoRequestDTO);
		adocao = adocaoService.adotar(adocao);

		AdocaoResponseDTO adocaoResponseDTO = new AdocaoResponseDTO(adocao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/adocoes/{id}")
				.buildAndExpand(adocao.getId()).toUri();

		return ResponseEntity.created(uri).body(adocaoResponseDTO);

	}

	@DeleteMapping(value = "/adocoes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para remover adoção por id")
	public ResponseEntity<Void> cancelarAdocao(@PathVariable("id") UUID id) {
		adocaoService.cancelarAdocao(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
