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
import com.github.wesleyav.adopet.entities.Animal;
import com.github.wesleyav.adopet.entities.dto.requests.AnimalRequestDTO;
import com.github.wesleyav.adopet.entities.dto.responses.AnimalResponseDTO;
import com.github.wesleyav.adopet.entities.dto.updates.AnimalUpdateDTO;
import com.github.wesleyav.adopet.repositories.AbrigoRepository;
import com.github.wesleyav.adopet.services.AnimalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Animal")
public class AnimalController {

	private AnimalService animalService;
	private AbrigoRepository abrigoRepository;

	public AnimalController(AnimalService animalService, AbrigoRepository abrigoRepository) {
		this.animalService = animalService;
		this.abrigoRepository = abrigoRepository;
	}

	@GetMapping(value = "/animais", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar todos os animais não adotados")
	public ResponseEntity<List<AnimalResponseDTO>> findAllNotAdotados() {
		List<Animal> animais = animalService.findAllNotAdotados();
		List<AnimalResponseDTO> animalResponseDTOs = animais.stream().map(AnimalResponseDTO::new)
				.collect(Collectors.toList());
		return new ResponseEntity<>(animalResponseDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/animais/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar animal por id")
	public ResponseEntity<Animal> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(animalService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/animais", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para cadastrar um novo animal")
	public ResponseEntity<AnimalResponseDTO> save(@RequestBody @Valid AnimalRequestDTO animalRequestDTO) {
		Abrigo abrigo = abrigoRepository.getReferenceById(animalRequestDTO.getAbrigoId());

		Animal animal = new Animal(animalRequestDTO, abrigo);
		animalService.save(animal);

//		Animal animal = animalRequestDTO.toEntity();
		Animal animalSalvo = animalService.save(animal);
		AnimalResponseDTO animalResponseDTO = new AnimalResponseDTO(animalSalvo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/animais/{id}")
				.buildAndExpand(animalSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(animalResponseDTO);
	}

	@DeleteMapping(value = "/animais/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para remover animal por id")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
		animalService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/animais/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um animal")
	public ResponseEntity<AnimalUpdateDTO> update(@PathVariable Integer id,
			@RequestBody AnimalRequestDTO animalRequestDTO) {
		Animal animal = animalRequestDTO.toEntity();
		animal.setId(id);
		Animal animalUpdate = animalService.update(id, animal);

		AnimalUpdateDTO animalUpdateDTO = new AnimalUpdateDTO(animalUpdate);

		return new ResponseEntity<>(animalUpdateDTO, HttpStatus.OK);
	}

}
