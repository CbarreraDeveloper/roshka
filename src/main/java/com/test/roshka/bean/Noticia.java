package com.test.roshka.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Noticia implements Serializable {

	private static final long serialVersionUID = -6198251215129652301L;

	public String fecha;
	public String enlace;
	public String enlaceFoto;
	public String titulo;
	public String resumen;

}
