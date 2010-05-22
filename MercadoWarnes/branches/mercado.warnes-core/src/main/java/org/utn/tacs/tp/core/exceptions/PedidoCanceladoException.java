package org.utn.tacs.tp.core.exceptions;

import org.utn.tacs.tp.core.domain.Pedido;

public class PedidoCanceladoException extends PedidoException {

	private static final long serialVersionUID = -3875321961114152028L;

	public PedidoCanceladoException(Pedido pedido) {
		super("El pedido '" + pedido + "' se encuentra cancelado.");
	}


}
