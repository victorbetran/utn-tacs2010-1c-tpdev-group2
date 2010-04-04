package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoEfectivizadoException extends BusinessModelException {

	private static final long serialVersionUID = 3669918947091035851L;

	public PedidoEfectivizadoException(Pedido pedido) {
		super("El pedido '" + pedido + "' ya ha sido efectivizado.");
	}


}
