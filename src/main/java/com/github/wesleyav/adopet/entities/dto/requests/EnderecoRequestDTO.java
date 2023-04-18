package com.github.wesleyav.adopet.entities.dto.requests;

import javax.validation.constraints.NotNull;

import com.github.wesleyav.adopet.entities.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoRequestDTO {

	private String logradouro;

	private String numero;

	private String cep;

	@NotNull(message = "O bairro é obrigatório")
	private BairroRequestDTO bairro;

	public static Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO) {

		if (enderecoRequestDTO == null) {
			return null;
		}
		
		Endereco endereco = new Endereco();

		if (enderecoRequestDTO.logradouro != null) {
			endereco.setLogradouro(enderecoRequestDTO.getLogradouro());
		}
		if (enderecoRequestDTO.numero != null) {
			endereco.setNumero(enderecoRequestDTO.getNumero());
		}
		if (enderecoRequestDTO.cep != null) {
			endereco.setCep(enderecoRequestDTO.getCep());
		}
		if (enderecoRequestDTO.getBairro() != null) {
			endereco.setBairro(BairroRequestDTO.toEntity(enderecoRequestDTO.getBairro()));
		}

		return endereco;

	}
}
