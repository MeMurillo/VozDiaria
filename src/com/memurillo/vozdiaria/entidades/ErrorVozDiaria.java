package com.memurillo.vozdiaria.entidades;

public class ErrorVozDiaria {
	
	private String code;
	private String detalle;
	
	public ErrorVozDiaria(String codigo, String detalle){
		code = codigo;
		this.detalle = detalle;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}
