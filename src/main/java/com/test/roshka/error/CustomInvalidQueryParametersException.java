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
public class CustomInvalidQueryParametersException extends Exception{

	private static final long serialVersionUID = -2650302484396886816L;
	private String codigo = "g268";
	private String error = "parámetros invalidos";
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

}
