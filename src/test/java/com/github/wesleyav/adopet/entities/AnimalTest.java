package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AnimalTest {

	@Test
	public void testSetId() {
		Animal animal = new Animal();
		animal.setId(1);
		assertEquals(1, animal.getId());
	}

	@Test
	public void testSetNome() {
		Animal animal = new Animal();
		animal.setNome("Animal");
		assertEquals("Animal", animal.getNome());
	}

	@Test
	public void testSetIdade() {
		Animal animal = new Animal();
		animal.setIdade("5");
		assertEquals("5", animal.getIdade());
	}

	@Test
	public void testSetDescricao() {
		Animal animal = new Animal();
		animal.setDescricao("Descricao");
		assertEquals("Descricao", animal.getDescricao());
	}

	@ParameterizedTest
	@ValueSource(booleans = { true, false })
	public void testSetAdotado(boolean adotado) {
		Animal animal = new Animal();
		animal.setAdotado(adotado);
		boolean result = animal.getAdotado();
		assertEquals(adotado, result);
	}

	@Test
	public void testSetImageUrl() {
		Animal animal = new Animal();
		animal.setImageUrl("http://exemplo.com/imagem.jpg");
		assertEquals("http://exemplo.com/imagem.jpg", animal.getImageUrl());
	}

	@Test
	public void testSetCreatedAt() {
		Animal animal = new Animal();
		Instant instant = Instant.parse("2023-04-12T11:14:27.188411600Z");
		animal.setCreatedAt(instant);
		assertEquals(instant, animal.getCreatedAt());
	}

	@Test
	public void testSetUpdatedAt() {
		Animal animal = new Animal();
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		animal.setUpdatedAt(instant);
		assertEquals(instant, animal.getUpdatedAt());
	}

	@Test
	public void testSetAbrigo() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");

		Endereco enderecoMock = mock(Endereco.class);
		when(enderecoMock.getId()).thenReturn(1);
		when(enderecoMock.getLogradouro()).thenReturn("Rua A");
		when(enderecoMock.getNumero()).thenReturn("123");
		when(enderecoMock.getCep()).thenReturn("11111111");

		Abrigo abrigoMock = mock(Abrigo.class);
		when(abrigoMock.getId()).thenReturn(1);
		when(abrigoMock.getNome()).thenReturn("Abrigo 1");
		when(abrigoMock.getCreatedAt()).thenReturn(instant);
		when(abrigoMock.getUpdatedAt()).thenReturn(instant);
		when(abrigoMock.getEndereco()).thenReturn(enderecoMock);

		assertEquals(1, abrigoMock.getId());
		assertEquals("Abrigo 1", abrigoMock.getNome());
		assertEquals(instant, abrigoMock.getCreatedAt());
		assertEquals(instant, abrigoMock.getUpdatedAt());

		assertEquals(1, abrigoMock.getEndereco().getId());
		assertEquals("Rua A", abrigoMock.getEndereco().getLogradouro());
		assertEquals("123", abrigoMock.getEndereco().getNumero());
		assertEquals("11111111", abrigoMock.getEndereco().getCep());

	}

}
