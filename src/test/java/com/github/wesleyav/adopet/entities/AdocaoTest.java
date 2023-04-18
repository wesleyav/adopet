package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class AdocaoTest {

	@Test
	public void testSetUUID() {
		Adocao adocao = new Adocao();
		UUID uuid = UUID.randomUUID();
		adocao.setId(uuid);
		assertEquals(uuid, adocao.getId());
	}

	@Test
	public void testSetAnimalId() {
		Adocao adocao = new Adocao();
		adocao.setAnimalId(1);
		assertEquals(1, adocao.getAnimalId());
	}

	@Test
	public void testSetTutorId() {
		Adocao adocao = new Adocao();
		adocao.setTutorId(1);
		assertEquals(1, adocao.getTutorId());
	}

	@Test
	public void testSetDataAdocao() {
		Adocao adocao = new Adocao();
		Instant instant = Instant.parse("2023-04-12T11:14:27.188411600Z");
		adocao.setDataAdocao(instant);
		assertEquals(instant, adocao.getDataAdocao());
	}
	
	@Test
	public void testToString() {
		Instant instant = Instant.parse("2023-04-12T11:14:27.188411600Z");
		UUID uuid = UUID.randomUUID();
		Adocao adocao = new Adocao(uuid, 1, 1, instant);

		String expected = "Adocao(id=" + adocao.getId() + ", animalId=" + adocao.getAnimalId() + ", tutorId=" + adocao.getTutorId() + ", dataAdocao=" + adocao.getDataAdocao() + ")";

		assertEquals(expected, adocao.toString());
	}

	@Test
	public void testHashCode() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		UUID uuid1 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998373");
		UUID uuid2 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998374");
		Adocao adocao1 = new Adocao(uuid1, 1, 1, instant);
		Adocao adocao2 = new Adocao(uuid1, 1, 1, instant);
		Adocao adocao3 = new Adocao(uuid2, 2, 2, instant);

		assertEquals(adocao1.hashCode(), adocao2.hashCode());
		assertNotEquals(adocao1.hashCode(), adocao3.hashCode());
	}

	@Test
	public void testEquals() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		
		UUID uuid1 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998373");
		UUID uuid2 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998374");
		Adocao adocao1 = new Adocao(uuid1, 1, 1, instant);
		Adocao adocao2 = new Adocao(uuid1, 1, 1, instant);
		Adocao adocao3 = new Adocao(uuid2, 2, 2, instant);

		assertTrue(adocao1.equals(adocao2));
		assertFalse(adocao1.equals(adocao3));
	}

	@Test
	public void testEqualsNull() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		UUID uuid = UUID.randomUUID();
		Adocao adocao = new Adocao(uuid, 1, 1, instant);

		assertFalse(adocao.equals(null));
	}

	@Test
	public void testEqualsDifferentClass() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		UUID uuid = UUID.randomUUID();
		Adocao adocao = new Adocao(uuid, 1, 1, instant);

		assertFalse(adocao.equals("Animal"));
	}

	@Test
	public void testEqualsSameObject() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		UUID uuid = UUID.randomUUID();
		Adocao adocao = new Adocao(uuid, 1, 1, instant);

		assertTrue(adocao.equals(adocao));
	}

	@Test
	public void testEqualsDifferentObjectSameId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		UUID uuid = UUID.randomUUID();
		Adocao adocao1 = new Adocao(uuid, 1, 1, instant);
		Adocao adocao2 = new Adocao(uuid, 1, 1, instant);
		
		assertTrue(adocao1.equals(adocao2));
	}

	@Test
	public void testEqualsDifferentObjectDifferentId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		UUID uuid1 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998373");
		UUID uuid2 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998374");
		Adocao adocao1 = new Adocao(uuid1, 1, 1, instant);
		Adocao adocao2 = new Adocao(uuid2, 1, 1, instant);
		
		assertFalse(adocao1.equals(adocao2));
	}
}
