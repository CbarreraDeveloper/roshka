package com.test.roshka.services;

import org.springframework.http.ResponseEntity;

public interface NoticiasService {

	public ResponseEntity<?> getObtenerNoticia(String textoBuscar) throws Exception;

}
