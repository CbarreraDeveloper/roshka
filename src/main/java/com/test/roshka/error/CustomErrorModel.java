package com.test.roshka.error;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomErrorModel implements Serializable {
	
	private static final long serialVersionUID = 1628432221665487991L;
	private String codigo;
	private String error;
	

}
