package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pedido.Pedido;

public class CancelarPedidoException extends BusinessModelException {

	private static final long serialVersionUID = -6420116209824733558L;

	public CancelarPedidoException(Pedido pedido) {
		super("El pedido '" + pedido.getId() + "' no puede cancelarse por estar " + pedido.getEstado() + ".");
	}

}
