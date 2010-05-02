package org.utn.tacs.tp.group2.domain.exceptions.pedido;

import org.utn.tacs.tp.group2.domain.pedido.Pedido;

public class PedidoCanceladoException extends PedidoException {

	private static final long serialVersionUID = -3875321961114152028L;

	public PedidoCanceladoException(Pedido pedido) {
		super("El pedido '" + pedido + "' se encuentra cancelado.");
	}


}
