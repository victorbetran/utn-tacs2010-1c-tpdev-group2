package org.utn.tacs.tp.group2.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class PedidoDTO {

	private List<String> piezas = new ArrayList<String>();
	private String id;
	private String estado;
	
	public PedidoDTO(Pedido pedido) {
		//TODO: ver xq las piezas vienen como proxy y pincha xq la session se cerro.
		for (Pieza pieza : pedido.getPiezas()) {
			this.piezas.add(pieza.getId().toString());
		}
		this.id = pedido.getId().toString();
		this.estado = pedido.getEstado().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PedidoDTO))
			return false;
		
		PedidoDTO dto = (PedidoDTO) obj;
		
		return this.id.equals(dto.id)
			   && this.estado.equals(dto.estado)
			   && this.piezas.equals(dto.piezas);
		
	}
	
	public List<String> getPiezas() {
		return this.piezas;
	}

	public String getId() {
		return id;
	}

	public String getEstado() {
		return estado;
	}
	
}
