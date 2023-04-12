package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class EnderecoTest {

	@Test
	public void testSetId() {
		Endereco endereco = new Endereco();
		endereco.setId(1);
		assertEquals(1, endereco.getId());
	}

	@Test
	public void testSetLogradouro() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Logradouro");
		assertEquals("Logradouro", endereco.getLogradouro());
	}

	@Test
	public void testSetNumero() {
		Endereco endereco = new Endereco();
		endereco.setNumero("1");
		assertEquals("1", endereco.getNumero());
	}

	@Test
	public void testSetCep() {
		Endereco endereco = new Endereco();
		endereco.setCep("11111111");
		assertEquals("11111111", endereco.getCep());
	}

	@Test
	public void testSetBairro() {
		Estado estadoMock = mock(Estado.class);
		when(estadoMock.getId()).thenReturn(1);
		when(estadoMock.getSigla()).thenReturn("SP");

		Cidade cidadeMock = mock(Cidade.class);
		when(cidadeMock.getId()).thenReturn(1);
		when(cidadeMock.getNome()).thenReturn("Cidade 1");
		when(cidadeMock.getEstado()).thenReturn(estadoMock);

		Bairro bairroMock = mock(Bairro.class);
		when(bairroMock.getId()).thenReturn(1);
		when(bairroMock.getNome()).thenReturn("Bairro 1");
		when(bairroMock.getCidade()).thenReturn(cidadeMock);
		when(bairroMock.getCidade().getEstado()).thenReturn(estadoMock);

		assertEquals(1, bairroMock.getId());
		assertEquals("Bairro 1", bairroMock.getNome());
		assertEquals(1, bairroMock.getCidade().getId());
		assertEquals("Cidade 1", bairroMock.getCidade().getNome());
		assertEquals(1, bairroMock.getCidade().getEstado().getId());
		assertEquals("SP", bairroMock.getCidade().getEstado().getSigla());
	}
}
