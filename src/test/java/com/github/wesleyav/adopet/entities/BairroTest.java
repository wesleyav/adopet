package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class BairroTest {

	@Test
	public void testSetId() {
		Bairro bairro = new Bairro();
		bairro.setId(1);
		assertEquals(1, bairro.getId());
	}

	@Test
	public void testSetNome() {
		Bairro bairro = new Bairro();
		bairro.setNome("Bairro 1");
		assertEquals("Bairro 1", bairro.getNome());
	}
	
	@Test
	public void testSetCidade() {
		Cidade cidadeMock = mock(Cidade.class);
		
		when(cidadeMock.getId()).thenReturn(1);
		when(cidadeMock.getNome()).thenReturn("Cidade 1");
		
		Bairro bairroMock = mock(Bairro.class);
		when(bairroMock.getId()).thenReturn(1);
		when(bairroMock.getNome()).thenReturn("Bairro 1");
		when(bairroMock.getCidade()).thenReturn(cidadeMock);
		
		
		assertEquals(1, bairroMock.getId());
		assertEquals("Bairro 1", bairroMock.getNome());
		assertEquals(1, bairroMock.getCidade().getId());
		assertEquals("Cidade 1", bairroMock.getCidade().getNome());
	}

}
