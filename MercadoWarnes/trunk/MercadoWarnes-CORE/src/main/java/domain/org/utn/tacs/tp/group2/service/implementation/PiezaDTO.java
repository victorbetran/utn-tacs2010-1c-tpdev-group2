package org.utn.tacs.tp.group2.service.implementation;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDTO {

	private String id;
	private String codigo;
	private String estado;
	private String autoOrigen;
	private String categoria;
	
	public PiezaDTO() {
	}

	public PiezaDTO(Pieza pieza) {
		this.id = pieza.getId().toString();
		this.codigo = pieza.getCodigo();
		this.estado = pieza.getEstado().toString();
		this.autoOrigen = pieza.getAutoOrigen().getId().toString();
		this.categoria = pieza.getCategoria();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public String getId() {
		return id;
	}
	
	public String getEstado() {
		return estado;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public String getAutoOrigen() {
		return autoOrigen;
	}
}
