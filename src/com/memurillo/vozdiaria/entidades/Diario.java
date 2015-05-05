package com.memurillo.vozdiaria.entidades;

public class Diario {
	private String nombre;
	private int logoDrawable;
	
	public Diario(String nombre, int logo){
		this.nombre = nombre;
		setLogoDrawable(logo);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getLogoDrawable() {
		return logoDrawable;
	}

	public void setLogoDrawable(int logoDrawable) {
		this.logoDrawable = logoDrawable;
	}
}
