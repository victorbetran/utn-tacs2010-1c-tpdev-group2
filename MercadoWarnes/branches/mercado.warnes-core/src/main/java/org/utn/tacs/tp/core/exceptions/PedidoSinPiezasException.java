package org.utn.tacs.tp.core.exceptions;

import org.utn.tacs.tp.core.domain.Pedido;

public class PedidoSinPiezasException extends PedidoException {

	private static final long serialVersionUID = 3669918947091035851L;
	
	public PedidoSinPiezasException(Pedido pedido) {
		super("El pedido '" + pedido + "' no tiene piezas");
	}

}
