package com.memurillo.vozdiaria.entidades;

import java.io.Serializable;

public class Noticia implements Serializable{
	private static final long serialVersionUID = 1L;
	private String titulo, link, descripcion, diario;
	
	public Noticia(String titulo, String link, String descripcion, String diario){
		this.titulo = titulo;
		this.setLink(link);
		this.setDescripcion(descripcion);
		this.setDiario(diario);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDiario() {
		return diario;
	}

	public void setDiario(String diario) {
		this.diario = diario;
	}
}
