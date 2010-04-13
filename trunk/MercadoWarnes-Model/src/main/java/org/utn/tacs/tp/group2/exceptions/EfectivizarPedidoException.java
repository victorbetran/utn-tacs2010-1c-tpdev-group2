package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pedido.Pedido;

public class EfectivizarPedidoException extends BusinessModelException {

	private static final long serialVersionUID = 5453405882929402725L;

	public EfectivizarPedidoException(Pedido pedido) {
		super("El pedido + '" + pedido.getId() + "' no puede efectivizarse por estar " + pedido.getEstado() + ".");
	}

}
