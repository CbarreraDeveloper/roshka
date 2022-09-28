package com.test.roshka.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.roshka.bean.Noticia;
import com.test.roshka.error.NotFoundException;
import com.test.roshka.services.NoticiaServicesImpl;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private NoticiaServicesImpl noticiaServices;

	@RequestMapping("/consulta")

	public ResponseEntity<?> getNoticias(@RequestParam("q") Optional<String> textoBuscarParam) {

		String textoBuscar = textoBuscarParam.get();

		Noticia[] arrayNoticias = null;

		arrayNoticias = noticiaServices.getObtenerNoticia(textoBuscar);

		if (arrayNoticias.length == 0) {
			throw new NotFoundException("", "g267",
					"error: No se encuentran noticias para el texto:{" + textoBuscar + "}");
		}

		return new ResponseEntity<>(arrayNoticias, HttpStatus.OK);
	}

}
