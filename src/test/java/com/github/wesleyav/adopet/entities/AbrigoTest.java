package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		Endereco enderecoMock = mock(Endereco.class);
		when(enderecoMock.getId()).thenReturn(1);
		when(enderecoMock.getLogradouro()).thenReturn("Rua A");
		when(enderecoMock.getNumero()).thenReturn("123");
		when(enderecoMock.getCep()).thenReturn("11111111");

		Abrigo abrigoMock = mock(Abrigo.class);
		when(abrigoMock.getId()).thenReturn(1);
		when(abrigoMock.getNome()).thenReturn("Abrigo XYZ");
		when(abrigoMock.getCreatedAt()).thenReturn(Instant.now());
		when(abrigoMock.getUpdatedAt()).thenReturn(Instant.now());
		when(abrigoMock.getEndereco()).thenReturn(enderecoMock);

		assertEquals(1, abrigoMock.getId());
		assertEquals(1, abrigoMock.getEndereco().getId());
		assertEquals("Rua A", abrigoMock.getEndereco().getLogradouro());
		assertEquals("123", abrigoMock.getEndereco().getNumero());
		assertEquals("11111111", abrigoMock.getEndereco().getCep());
	}
}
