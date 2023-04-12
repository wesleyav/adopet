package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

	@Test
	public void testToString() {
		Estado estado = new Estado(1, "SP");
		String expected = "Estado(id=" + estado.getId() + ", sigla=" + estado.getSigla() + ")";
		assertEquals(expected, estado.toString());
	}

	@Test
	public void testHashCode() {
		Estado estado1 = new Estado(1, "SP");
		Estado estado2 = new Estado(1, "SP");
		Estado estado3 = new Estado(2, "RN");

		assertEquals(estado1.hashCode(), estado2.hashCode());
		assertNotEquals(estado1.hashCode(), estado3.hashCode());
	}

	@Test
	public void testEquals() {
		Estado estado1 = new Estado(1, "SP");
		Estado estado2 = new Estado(1, "SP");
		Estado estado3 = new Estado(2, "RN");

		assertTrue(estado1.equals(estado2));
		assertFalse(estado1.equals(estado3));
	}

	@Test
	public void testEqualsNull() {
		Estado estado = new Estado(1, "SP");
		assertFalse(estado.equals(null));
	}

	@Test
	public void testEqualsDifferentClass() {
		Estado estado = new Estado(1, "SP");
		assertFalse(estado.equals("SP"));
	}

	@Test
	public void testEqualsSameObject() {
		Estado estado = new Estado(1, "SP");
		assertTrue(estado.equals(estado));
	}

	@Test
	public void testEqualsDifferentObjectSameId() {
		Estado estado1 = new Estado(1, "SP");
		Estado estado2 = new Estado(1, "RJ");
		assertTrue(estado1.equals(estado2));
	}

	@Test
	public void testEqualsDifferentObjectDifferentId() {
		Estado estado1 = new Estado(1, "SP");
		Estado estado2 = new Estado(2, "SP");
		assertFalse(estado1.equals(estado2));
	}
}
