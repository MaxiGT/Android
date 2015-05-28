package com.example.clase3;

public class Noticia {

	String titulo;
	String subTitulo;
	Integer icono;
	
		public Noticia(String titulo, String subTitulo, Integer icono) {
			this.titulo = titulo;
			this.subTitulo = subTitulo;
		}
		
	public String getTitulo(){
		return this.titulo;
	}
	
	public String getsubTitulo() {
		return this.subTitulo;
	}
		
}
