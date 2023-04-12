package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class TutorTest {

	@Test
	public void testSetId() {
		Tutor tutor = new Tutor();
		tutor.setId(123);
		assertEquals(123, tutor.getId());
	}

	@Test
	public void testSetNome() {
		Tutor tutor = new Tutor();
		tutor.setNome("Joao");
		assertEquals("Joao", tutor.getNome());
	}

	@Test
	public void testSetTelefone() {
		Tutor tutor = new Tutor();
		tutor.setTelefone("11111111111");
		assertEquals("11111111111", tutor.getTelefone());
	}

	@Test
	public void testSetCidade() {
		Tutor tutor = new Tutor();
		tutor.setCidade("São Paulo");
		assertEquals("São Paulo", tutor.getCidade());
	}

	@Test
	public void testSetSobre() {
		Tutor tutor = new Tutor();
		tutor.setSobre("Descrição");
		assertEquals("Descrição", tutor.getSobre());
	}

	@Test
	public void testSetCreatedAt() {
		Tutor tutor = new Tutor();
		Instant instant = Instant.parse("2023-04-12T11:14:27.188411600Z");
		tutor.setCreatedAt(instant);
		assertEquals(instant, tutor.getCreatedAt());
	}

	@Test
	public void testSetUpdatedAt() {
		Tutor tutor = new Tutor();
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		tutor.setUpdatedAt(instant);
		assertEquals(instant, tutor.getUpdatedAt());
	}

	@Test
	public void testSetImageUrl() {
		Tutor tutor = new Tutor();
		tutor.setImageUrl("http://exemplo.com/imagem.jpg");
		assertEquals("http://exemplo.com/imagem.jpg", tutor.getImageUrl());
	}

}
