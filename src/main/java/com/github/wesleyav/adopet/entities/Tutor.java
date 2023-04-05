package com.github.wesleyav.adopet.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

	private String imageUrl;

}
