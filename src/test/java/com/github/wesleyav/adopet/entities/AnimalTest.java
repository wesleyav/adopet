package com.github.wesleyav.adopet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.wesleyav.adopet.entities.dto.requests.AnimalRequestDTO;

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
	    // cria um objeto Abrigo para ser usado no teste
	    Abrigo abrigo = new Abrigo();
	    abrigo.setId(1);
	    abrigo.setNome("Abrigo Feliz");

	    // cria um objeto Animal e chama o método setAbrigo com o objeto Abrigo criado
	    Animal animal = new Animal();
	    animal.setAbrigo(abrigo);

	    // verifica se o valor da propriedade abrigo do objeto Animal é igual ao objeto Abrigo criado
	    assertEquals(abrigo, animal.getAbrigo());
	}

	
	@Test
	public void testAnimalRequestDTOAbrigo() {
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
		
		AnimalRequestDTO animalRequestDTOMock = mock(AnimalRequestDTO.class);
		when(animalRequestDTOMock.getNome()).thenReturn("nome");
		when(animalRequestDTOMock.getIdade()).thenReturn("1");
		when(animalRequestDTOMock.getDescricao()).thenReturn("descricao");
		when(animalRequestDTOMock.getAdotado()).thenReturn(false);
		when(animalRequestDTOMock.getImageUrl()).thenReturn("http://exemplo.com/imagem.jpg");
		when(animalRequestDTOMock.getAbrigoId()).thenReturn(1);
		
		assertEquals("nome", animalRequestDTOMock.getNome());
		assertEquals("1", animalRequestDTOMock.getIdade());
		assertEquals("descricao", animalRequestDTOMock.getDescricao());
		assertEquals(false, animalRequestDTOMock.getAdotado());
		assertEquals("http://exemplo.com/imagem.jpg", animalRequestDTOMock.getImageUrl());
		assertEquals(1, animalRequestDTOMock.getAbrigoId());
		
	}

	@Test
	public void testToString() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Animal animal = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);

		String expected = "Animal(id=" + animal.getId() + ", nome=" + animal.getNome() + ", idade=" + animal.getIdade()
				+ ", descricao=" + animal.getDescricao() + ", adotado=" + animal.getAdotado() + ", imageUrl="
				+ animal.getImageUrl() + ", createdAt=" + animal.getCreatedAt() + ", updatedAt=" + animal.getUpdatedAt()
				+ ", abrigo=" + animal.getAbrigo() + ")";

		assertEquals(expected, animal.toString());
	}

	@Test
	public void testHashCode() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Animal animal1 = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);
		Animal animal2 = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);
		Animal animal3 = new Animal(2, "animal 3", "3", "descricao 3", false, "http://exemplo.com/imagem3.jpg", instant,
				instant, null);

		assertEquals(animal1.hashCode(), animal2.hashCode());
		assertNotEquals(animal1.hashCode(), animal3.hashCode());
	}

	@Test
	public void testEquals() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");

		Animal animal1 = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);
		Animal animal2 = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);
		Animal animal3 = new Animal(2, "animal 3", "3", "descricao 3", false, "http://exemplo.com/imagem3.jpg", instant,
				instant, null);

		assertTrue(animal1.equals(animal2));
		assertFalse(animal1.equals(animal3));
	}

	@Test
	public void testEqualsNull() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Animal animal = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);

		assertFalse(animal.equals(null));
	}

	@Test
	public void testEqualsDifferentClass() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Animal animal = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);

		assertFalse(animal.equals("Animal"));
	}

	@Test
	public void testEqualsSameObject() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Animal animal = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);

		assertTrue(animal.equals(animal));
	}

	@Test
	public void testEqualsDifferentObjectSameId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Animal animal1 = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);
		Animal animal2 = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);

		assertTrue(animal1.equals(animal2));
	}

	@Test
	public void testEqualsDifferentObjectDifferentId() {
		Instant instant = Instant.parse("2023-04-12T11:19:42.861657100Z");
		Animal animal1 = new Animal(1, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);
		Animal animal2 = new Animal(2, "animal 1", "9", "descricao", false, "http://exemplo.com/imagem.jpg", instant,
				instant, null);

		assertFalse(animal1.equals(animal2));
	}
	
	@Test
	public void testConstructorWithAnimalRequestDTOAndAbrigo() {
	    // Arrange
	    Abrigo abrigo = new Abrigo();
	    abrigo.setId(1);
	    abrigo.setNome("Abrigo Teste");

	    AnimalRequestDTO animalRequestDTO = new AnimalRequestDTO();
	    animalRequestDTO.setNome("Animal Teste");
	    animalRequestDTO.setIdade("2 anos");
	    animalRequestDTO.setDescricao("Descrição do Animal Teste");
	    animalRequestDTO.setAdotado(true);
	    animalRequestDTO.setImageUrl("https://exemplo.com/animal-teste.jpg");

	    // Act
	    Animal animal = new Animal(animalRequestDTO, abrigo);

	    // Assert
	    assertEquals(animalRequestDTO.getNome(), animal.getNome());
	    assertEquals(animalRequestDTO.getIdade(), animal.getIdade());
	    assertEquals(animalRequestDTO.getDescricao(), animal.getDescricao());
	    assertEquals(animalRequestDTO.getAdotado(), animal.getAdotado());
	    assertEquals(animalRequestDTO.getImageUrl(), animal.getImageUrl());
	    assertEquals(abrigo, animal.getAbrigo());
	}
	
	

}
