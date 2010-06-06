package org.utn.tacs.tp.group2.service.implementation;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDTO {

	private String codigo;
	
	public PiezaDTO() {
	}

	public PiezaDTO(Pieza pieza) {
		this.codigo = pieza.getCodigo();
	}
	
	public String getCodigo() {
		return codigo;
	}

}
