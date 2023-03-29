package com.github.wesleyav.adopet.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Não encontrado. Id " + id);
	}

}
