package com.github.wesleyav.adopet.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(of = { "id", "nome", "email", "telefone", "cidade", "sobre", "createdAt", "updatedAt", "imageUrl", "senha", "animais" })
@Entity
@Table(name = "tutor")
public class Tutor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String email;

	private String telefone;

	private String cidade;

	private String sobre;

	private Instant createdAt;

	private Instant updatedAt;

	private String imageUrl;

	private String senha;

	@ManyToMany(mappedBy = "tutores")
	@JsonIgnore
	private List<Animal> animais = new ArrayList<>();

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
		Tutor other = (Tutor) obj;
		return Objects.equals(id, other.id);
	}

}
