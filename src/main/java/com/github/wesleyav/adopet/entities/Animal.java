package com.github.wesleyav.adopet.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.wesleyav.adopet.entities.dto.requests.AnimalRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(of = { "id", "nome", "idade", "descricao", "adotado", "imageUrl", "createdAt", "updatedAt", "abrigo" })
@Entity
@Table(name = "animal")
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String idade;

	private String descricao;

	private Boolean adotado;

	private String imageUrl;

	private Instant createdAt;

	private Instant updatedAt;

	@ManyToMany
	@JoinTable(name = "adocao", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "animal_id"))
	@JsonIgnore
	private List<Tutor> tutores = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "abrigo_id", referencedColumnName = "id")
	private Abrigo abrigo;

	public Animal(AnimalRequestDTO animalRequestDTO, Abrigo abrigo) {
		this.nome = animalRequestDTO.getNome();
		this.idade = animalRequestDTO.getIdade();
		this.descricao = animalRequestDTO.getDescricao();
		this.adotado = animalRequestDTO.getAdotado();
		this.imageUrl = animalRequestDTO.getImageUrl();
		this.abrigo = abrigo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(id, other.id);
	}

}
