package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoSinPiezasException extends BusinessModelException {

	private static final long serialVersionUID = 3669918947091035851L;
	
	public PedidoSinPiezasException(Pedido pedido) {
		super("El pedido : " + pedido + " no tiene piezas");
	}

}
