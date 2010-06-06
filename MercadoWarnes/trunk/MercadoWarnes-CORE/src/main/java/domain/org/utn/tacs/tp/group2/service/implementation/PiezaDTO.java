package org.utn.tacs.tp.group2.service.implementation;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDTO {
	private String id;
	private String codigo;
	private String estado;
	
	public PiezaDTO() {
	}

	public PiezaDTO(Pieza pieza) {
		this.id = pieza.getId().toString();
		this.codigo = pieza.getCodigo();
		this.estado = pieza.getEstado().toString();
	}
	
	public String getCodigo() {
		return codigo;
	}

}
