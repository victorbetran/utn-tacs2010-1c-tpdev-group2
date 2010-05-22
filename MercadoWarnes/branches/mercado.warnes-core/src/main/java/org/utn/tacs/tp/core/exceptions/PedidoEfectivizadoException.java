package org.utn.tacs.tp.core.exceptions;

import org.utn.tacs.tp.core.domain.Pedido;

public class PedidoEfectivizadoException extends PedidoException {

	private static final long serialVersionUID = 3669918947091035851L;

	public PedidoEfectivizadoException(Pedido pedido) {
		super("El pedido '" + pedido + "' ya ha sido efectivizado.");
	}


}
