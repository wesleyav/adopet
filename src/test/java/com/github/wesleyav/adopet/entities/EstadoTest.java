package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EstadoTest {

	@Test
	public void testSetId() {
		Estado estado = new Estado();
		estado.setId(1);
		assertEquals(1, estado.getId());
	}

	@Test
	public void testSetSigla() {
		Estado estado = new Estado();
		estado.setSigla("SP");
		assertEquals("SP", estado.getSigla());
	}
}
