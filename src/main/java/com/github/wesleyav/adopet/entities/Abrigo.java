package com.github.wesleyav.adopet.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(of = { "id", "nome", "email", "createdAt", "updatedAt" })
@Entity
@Table(name = "abrigo")
public class Abrigo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String email;

	private Instant createdAt;

	private Instant updatedAt;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "endereco", referencedColumnName = "id")
	private Endereco endereco;

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
		Abrigo other = (Abrigo) obj;
		return Objects.equals(id, other.id);
	}

}
