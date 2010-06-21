package ar.edu.utn.frba.tacs.warnes;

import java.io.Serializable;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1043832429321958028L;
	private String nombre;
	private String apellido;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
