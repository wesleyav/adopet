package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class AbrigoTest {

	@Test
	public void testSetId() {
		Abrigo abrigo = new Abrigo();
		abrigo.setId(123);
		assertEquals(123, abrigo.getId());
	}

	@Test
	public void testSetNome() {
		Abrigo abrigo = new Abrigo();
		abrigo.setNome("Abrigo");
		assertEquals("Abrigo", abrigo.getNome());
	}

	@Test
	public void testSetEmail() {
		Abrigo abrigo = new Abrigo();
		abrigo.setEmail("teste@teste.com.br");
		assertEquals("teste@teste.com.br", abrigo.getEmail());
	}

	@Test
	public void testSetCreatedAt() {
		Abrigo abrigo = new Abrigo();
		Instant instant = Instant.parse("2023-04-12T11:14:27.188411600Z");
		abrigo.setCreatedAt(instant);
		assertEquals(instant, abrigo.getCreatedAt());
	}

	@Test
	public void testSetUpdatedAt() {
		Abrigo abrigo = new Abrigo();
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		abrigo.setUpdatedAt(instant);
		assertEquals(instant, abrigo.getUpdatedAt());
	}
	
	@Test
	public void testSetEndereco() {
	    Abrigo abrigo = new Abrigo();
	    abrigo.setId(1);
	    abrigo.setNome("Abrigo Feliz");
	    
	    Estado estado = new Estado();
	    estado.setSigla("SP");
	    
	    Cidade cidade = new Cidade();
	    cidade.setEstado(estado);
	    
	    Bairro bairro = new Bairro();
	    bairro.setCidade(cidade);
	    
	    Endereco endereco = new Endereco();
	    endereco.setBairro(bairro);
	    
	    abrigo.setEndereco(endereco);

	    Animal animal = new Animal();
	    animal.setAbrigo(abrigo);

	    assertEquals(abrigo.getEndereco(), animal.getAbrigo().getEndereco());
	}


	@Test
	public void testToString() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo = new Abrigo(1, "nome", "email@email.com", instant, instant, null);
		
		String expected = "Abrigo(id=" + abrigo.getId() + ", nome=" + abrigo.getNome() + ", email=" + abrigo.getEmail() + ", createdAt=" + abrigo.getCreatedAt() + ", updatedAt=" + abrigo.getUpdatedAt() + ")";
		
		assertEquals(expected, abrigo.toString());
	}

	@Test
	public void testHashCode() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo1 = new Abrigo(1, "nome", "email@email.com", instant, instant, null);
		Abrigo abrigo2 = new Abrigo(1, "nome", "email@email.com", instant, instant, null);
		Abrigo abrigo3 = new Abrigo(2, "nome3", "email3@email.com", instant, instant, null);

		assertEquals(abrigo1.hashCode(), abrigo2.hashCode());
		assertNotEquals(abrigo1.hashCode(), abrigo3.hashCode());
	}

	@Test
	public void testEquals() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo1 = new Abrigo(1, "nome", "email@email.com", instant, instant, null);
		Abrigo abrigo2 = new Abrigo(1, "nome", "email@email.com", instant, instant, null);
		Abrigo abrigo3 = new Abrigo(2, "nome3", "email3@email.com", instant, instant, null);

		assertTrue(abrigo1.equals(abrigo2));
		assertFalse(abrigo1.equals(abrigo3));
	}

	@Test
	public void testEqualsNull() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo = new Abrigo(1, "nome", "email@email.com", instant, instant, null);

		assertFalse(abrigo.equals(null));
	}

	@Test
	public void testEqualsDifferentClass() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo = new Abrigo(1, "nome", "email@email.com", instant, instant, null);

		assertFalse(abrigo.equals("Abrigo"));
	}

	@Test
	public void testEqualsSameObject() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo = new Abrigo(1, "nome", "email@email.com", instant, instant, null);

		assertTrue(abrigo.equals(abrigo));
	}

	@Test
	public void testEqualsDifferentObjectSameId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo1 = new Abrigo(1, "nome", "email@email.com", instant, instant, null);
		Abrigo abrigo2 = new Abrigo(1, "nome", "email@email.com", instant, instant, null);

		assertTrue(abrigo1.equals(abrigo2));
	}

	@Test
	public void testEqualsDifferentObjectDifferentId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Abrigo abrigo1 = new Abrigo(1, "nome", "email@email.com", instant, instant, null);
		Abrigo abrigo2 = new Abrigo(2, "nome", "email@email.com", instant, instant, null);

		assertFalse(abrigo1.equals(abrigo2));
	}
}
