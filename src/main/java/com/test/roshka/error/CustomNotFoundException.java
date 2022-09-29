package com.test.roshka.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomNotFoundException extends Exception {

	private static final long serialVersionUID = 6645901434818909174L;

	private String codigo = "g267";
	private String textoBusqueda;
	private HttpStatus httpStatus = HttpStatus.NOT_FOUND;
	private String error = "No se encuentra noticias para el texto: ";

	public CustomNotFoundException(String textoBusqueda) {
		super();
		this.error = error + textoBusqueda;

	}

}