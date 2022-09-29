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
public class CustomInternalServerErrorException extends Exception {
	

	private static final long serialVersionUID = -7041388196973026088L;
	private String codigo = "g100";
	private String error = "Error interno del servidor";
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
}
