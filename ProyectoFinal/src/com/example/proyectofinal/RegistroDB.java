package com.example.proyectofinal;

public class RegistroDB {
	
	Integer id;
	String nombre;
	String direccion;
	String observaciones;
	String foto;
	String telefono;
	Float rate;
	
	public RegistroDB(Integer id, String nombre, String direccion, String observaciones, String foto, String telefono, Float rate) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.observaciones = observaciones;
		this.foto = foto;
		this.telefono = telefono;
		this.rate = rate;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getFoto() {
		return foto;
	}

	public String getTelefono() {
		return telefono;
	}

	public Float getRate() {
		return rate;
	}

}
