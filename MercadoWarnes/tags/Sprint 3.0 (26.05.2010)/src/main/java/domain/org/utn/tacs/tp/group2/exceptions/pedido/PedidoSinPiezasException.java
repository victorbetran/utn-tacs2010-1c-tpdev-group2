package org.utn.tacs.tp.group2.exceptions.pedido;

import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoSinPiezasException extends PedidoException {

	private static final long serialVersionUID = 3669918947091035851L;
	
	public PedidoSinPiezasException(Pedido pedido) {
		super("El pedido '" + pedido + "' no tiene piezas");
	}

}
