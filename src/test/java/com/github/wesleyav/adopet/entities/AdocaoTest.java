package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
