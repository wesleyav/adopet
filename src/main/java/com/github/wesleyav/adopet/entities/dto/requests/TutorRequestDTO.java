package com.github.wesleyav.adopet.entities.dto.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.github.wesleyav.adopet.entities.Tutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TutorRequestDTO {

	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 3, max = 45, message = "O nome deverá ter no mínimo {min} caracteres e no máximo {max} caracteres")
	private String nome;

	@Email
	@NotBlank(message = "O nome é obrigatório")
	private String email;

	@NotBlank(message = "O telefone é obrigatório")
	@Length(min = 10, max = 11, message = "O telefone deve conter 11 dígitos")
	@Pattern(regexp = "\\d{10,11}", message = "Para telefone fixo 10 dígitos, para telefone móvel 11 dígitos")
	private String telefone;

	@NotBlank(message = "Sobre é obrigatório")
	private String sobre;

	private String imageUrl;

	private String cidade;

	public Tutor toEntity() {
		Tutor tutor = new Tutor();
		tutor.setNome(this.nome);
		tutor.setEmail(this.email);
		tutor.setTelefone(this.telefone);
		tutor.setSobre(this.sobre);
		tutor.setImageUrl(this.imageUrl);
		tutor.setCidade(this.cidade);
		return tutor;
	}

}
