package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class AdocaoTest {

	@Test
	public void testSetUUID() {
		Adocao adocao = new Adocao();
		
		UUID uuid = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998373");

		adocao.setId(uuid);
		assertEquals(uuid, adocao.getId());
	}
	
	@Test
	public void testSetTutorId() {
		Adocao adocao = new Adocao();
		Instant instant = Instant.parse("2023-04-12T11:14:27.188411600Z");
		Tutor tutor = new Tutor(1, "nome", "email", "9999999999999", "cidade", "sobre", instant, instant, "img", "senha", null);
		
		adocao.setTutorId(tutor);
		assertEquals(1, adocao.getTutorId().getId());
	}
	
	@Test
	public void testSetAnimalId() {
		Adocao adocao = new Adocao();
		Instant instant = Instant.parse("2023-04-12T11:14:27.188411600Z");
		Animal animal = new Animal(1, "nome", "1", "descricao", false, "img", instant, instant, null, null);
		
		adocao.setAnimalId(animal);
		assertEquals(1, adocao.getAnimalId().getId());
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
		UUID uuid = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998373");
		Tutor tutor = new Tutor(1, "nome", "email", "9999999999999", "cidade", "sobre", instant, instant, "img", "senha", null);
		Animal animal = new Animal(1, "nome", "1", "descricao", false, "img", instant, instant, null, null);
		Adocao adocao = new Adocao(uuid, tutor, animal, instant);

		String expected = "Adocao(id=" + adocao.getId() + ", tutorId=" + adocao.getTutorId() + ", animalId=" + adocao.getAnimalId() + ", dataAdocao=" + adocao.getDataAdocao() + ")";

		assertEquals(expected, adocao.toString());
	}
	
	@Test
	public void testHashCode() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		
		UUID uuid1 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998373");
		UUID uuid2 = UUID.fromString("872dcaff-26d8-4327-a51d-73e685998374");
		
		Tutor tutor = new Tutor(1, "nome", "email", "9999999999999", "cidade", "sobre", instant, instant, "img", "senha", null);
		Animal animal = new Animal(1, "nome", "1", "descricao", false, "img", instant, instant, null, null);
		
		Adocao adocao1 = new Adocao(uuid1, tutor, animal, instant);
		Adocao adocao2 = new Adocao(uuid1, tutor, animal, instant);
		Adocao adocao3 = new Adocao(uuid2, tutor, animal, instant);

		assertEquals(adocao1.hashCode(), adocao2.hashCode());
		assertNotEquals(adocao1.getId().hashCode(), adocao3.getId().hashCode());
	}

}
