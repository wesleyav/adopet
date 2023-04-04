package com.github.wesleyav.adopet.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "animal")
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 3, max = 15, message = "O nome deverá ter no mínimo {min} caracteres e no máximo {max} caracteres")
	private String nome;

	@NotNull(message = "A idade é obrigatória")
	private Integer idade;

	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "created_at")
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "abrigo", referencedColumnName = "id")
	private Abrigo abrigo;

}
