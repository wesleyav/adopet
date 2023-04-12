package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class CidadeTest {

	@Test
	public void testSetId() {
		Cidade cidade = new Cidade();
		cidade.setId(1);
		assertEquals(1, cidade.getId());
	}

	@Test
	public void testSetNome() {
		Cidade cidade = new Cidade();
		cidade.setNome("Cidade 1");
		assertEquals("Cidade 1", cidade.getNome());
	}

	@Test
	public void testSetEstado() {
		Estado estadoMock = mock(Estado.class);
		when(estadoMock.getId()).thenReturn(1);
		when(estadoMock.getSigla()).thenReturn("SP");

		Cidade cidadeMock = mock(Cidade.class);
		when(cidadeMock.getId()).thenReturn(1);
		when(cidadeMock.getNome()).thenReturn("Cidade 1");
		when(cidadeMock.getEstado()).thenReturn(estadoMock);

		assertEquals(1, cidadeMock.getId());
		assertEquals("Cidade 1", cidadeMock.getNome());
		assertEquals(1, cidadeMock.getEstado().getId());
		assertEquals("SP", cidadeMock.getEstado().getSigla());
	}
}
