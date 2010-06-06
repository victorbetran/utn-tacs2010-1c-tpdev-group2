package org.utn.tacs.tp.group2.service.implementation;

import java.io.Serializable;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDTO implements Serializable{

	private String codigo;
	private String estado;
	
	public PiezaDTO() {
	}

	public PiezaDTO(Pieza pieza) {
		this.codigo = pieza.getCodigo();
		this.estado = pieza.getEstado().toString();
	}
	
	public String getCodigo() {
		return codigo;
	}

}
