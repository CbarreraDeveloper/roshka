package com.test.roshka.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsControllerAdvice {
	
		@ExceptionHandler(CustomInvalidQueryParametersException.class)
		public ResponseEntity<CustomErrorModel> generateInvalidParameterException (
					CustomInvalidQueryParametersException customInvalidQueryParametersException){
			
			CustomErrorModel customErrorModel = new CustomErrorModel();
			customErrorModel.setCodigo(customInvalidQueryParametersException.getCodigo());
			customErrorModel.setError(customInvalidQueryParametersException.getError());
			
			return new ResponseEntity<CustomErrorModel>(customErrorModel, customInvalidQueryParametersException.getHttpStatus());
		}
		
		@ExceptionHandler(CustomInternalServerErrorException.class)
		public ResponseEntity<CustomErrorModel> generateInternalServerErrorException (
				CustomInternalServerErrorException customInternalServerErrorException){
			
			CustomErrorModel customErrorModel = new CustomErrorModel();
			customErrorModel.setCodigo(customInternalServerErrorException.getCodigo());
			customErrorModel.setError(customInternalServerErrorException.getError());
			
			return new ResponseEntity<CustomErrorModel>(customErrorModel, customInternalServerErrorException.getHttpStatus());
		}
		
		

		@ExceptionHandler(CustomNotFoundException.class)
		public ResponseEntity<CustomErrorModel> generateCustomNotFoundException (
				CustomNotFoundException notFoundException){
			
			CustomErrorModel customErrorModel = new CustomErrorModel();
			customErrorModel.setCodigo(notFoundException.getCodigo());
			customErrorModel.setError(notFoundException.getError());
			
			return new ResponseEntity<CustomErrorModel>(customErrorModel, notFoundException.getHttpStatus());
		}
		
		
		
}
