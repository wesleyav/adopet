package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	public void testSetEmail() {
		Tutor tutor = new Tutor();
		tutor.setEmail("email@teste.com.br");
		assertEquals("email@teste.com.br", tutor.getEmail());
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
	
	@Test
	public void testSetSenha() {
		Tutor tutor = new Tutor();
		tutor.setSenha("senha");
		assertEquals("senha", tutor.getSenha());
	}

	@Test
	public void testToString() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor = new Tutor(1, "Tutor", "email@teste.com.br", "Cidade", "Sobre", null, instant, instant, null, "senha");
		String expected = "Tutor(id=" + tutor.getId() + ", nome=" + tutor.getNome() + ", email=" + tutor.getEmail()
				+ ", telefone=" + tutor.getTelefone() + ", cidade=" + tutor.getCidade() + ", sobre=" + tutor.getSobre()
				+ ", imageUrl=" + tutor.getImageUrl() + ")";
		assertEquals(expected, tutor.toString());
	}
	
	@Test
	public void testHashCode() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor1 = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		Tutor tutor2 = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		Tutor tutor3 = new Tutor(2, "Tutor 3", "email3@teste.com.br", "5511333333333", "Cidade 3", "Sobre 3", instant, instant, "http://exemplo.com/imagem3.jpg", "senha");
		
		assertEquals(tutor1.hashCode(), tutor2.hashCode());
		assertNotEquals(tutor1.hashCode(), tutor3.hashCode());
	}

	@Test
	public void testEquals() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor1 = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		Tutor tutor2 = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		Tutor tutor3 = new Tutor(2, "Tutor 3", "email3@teste.com.br", "5511333333333", "Cidade 3", "Sobre 3", instant, instant, "http://exemplo.com/imagem3.jpg", "senha");
		
		assertTrue(tutor1.equals(tutor2));
		assertFalse(tutor1.equals(tutor3));
	}

	@Test
	public void testEqualsNull() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		
		assertFalse(tutor.equals(null));
	}

	@Test
	public void testEqualsDifferentClass() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		
		assertFalse(tutor.equals("Tutor"));
	}

	@Test
	public void testEqualsSameObject() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		
		assertTrue(tutor.equals(tutor));
	}

	@Test
	public void testEqualsDifferentObjectSameId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor1 = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		Tutor tutor2 = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		
		assertTrue(tutor1.equals(tutor2));
	}

	@Test
	public void testEqualsDifferentObjectDifferentId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Tutor tutor1 = new Tutor(1, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		Tutor tutor2 = new Tutor(2, "Tutor", "email@teste.com.br", "5511111111111", "Cidade", "Sobre", instant, instant, "http://exemplo.com/imagem.jpg", "senha");
		
		assertFalse(tutor1.equals(tutor2));
	}

}
