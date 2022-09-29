package com.test.roshka.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.roshka.services.NoticiaServicesImpl;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private NoticiaServicesImpl noticiaServices;

	@RequestMapping("/consulta")
	public ResponseEntity<?> getNoticias(@RequestParam("q") Optional<String> textoBuscarParam) throws Exception {
		return noticiaServices.getObtenerNoticia(textoBuscarParam.get());
	}
}