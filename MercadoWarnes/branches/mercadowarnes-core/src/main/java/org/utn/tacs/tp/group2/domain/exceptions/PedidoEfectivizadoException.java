package org.utn.tacs.tp.group2.domain.exceptions;

import org.utn.tacs.tp.group2.domain.Pedido;

public class PedidoEfectivizadoException extends PedidoException {

	private static final long serialVersionUID = 3669918947091035851L;

	public PedidoEfectivizadoException(Pedido pedido) {
		super("El pedido '" + pedido + "' ya ha sido efectivizado.");
	}


}
