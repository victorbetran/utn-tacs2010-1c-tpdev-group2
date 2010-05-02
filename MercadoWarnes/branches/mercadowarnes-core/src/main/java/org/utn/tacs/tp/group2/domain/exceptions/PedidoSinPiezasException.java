package org.utn.tacs.tp.group2.domain.exceptions;

import org.utn.tacs.tp.group2.domain.pedido.Pedido;

public class PedidoSinPiezasException extends PedidoException {

	private static final long serialVersionUID = 3669918947091035851L;
	
	public PedidoSinPiezasException(Pedido pedido) {
		super("El pedido '" + pedido + "' no tiene piezas");
	}

}
