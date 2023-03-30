package com.github.wesleyav.adopet.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tutor")
public class Tutor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 3, max = 45, message = "O nome deverá ter no mínimo {min} caracteres e no máximo {max} caracteres")
	private String nome;

	@NotBlank(message = "O telefone é obrigatório")
	@Length(min = 10, max = 11, message = "O telefone deve conter 11 dígitos")
	@Pattern(regexp = "\\d{10,11}", message = "Para telefone fixo 10 dígitos, para telefone móvel 11 dígitos")
	private String telefone;

	@NotBlank(message = "A cidade é obrigatória")
	private String cidade;

	@NotBlank(message = "A descrição sobre é obrigatória")
	private String sobre;

	@Column(name = "created_at")
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	private String foto;

	public Tutor() {
	}

	public Tutor(Integer id, String nome, String telefone, String cidade, String sobre, Instant createdAt,
			Instant updatedAt, String foto) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.cidade = cidade;
		this.sobre = sobre;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.foto = foto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		Tutor other = (Tutor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", cidade=" + cidade + ", sobre="
				+ sobre + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", foto=" + foto + "]";
	}

}
