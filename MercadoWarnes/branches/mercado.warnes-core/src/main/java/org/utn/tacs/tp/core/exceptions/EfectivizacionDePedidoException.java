package org.utn.tacs.tp.core.exceptions;

import org.utn.tacs.tp.core.domain.Pedido;

public class EfectivizacionDePedidoException extends PedidoException {

	private static final long serialVersionUID = 7428027625586568079L;

	public EfectivizacionDePedidoException(Pedido pedido, PiezaException e) {
		super("Error al efectivizar el pedido '" + pedido + "'.");
		this.setStackTrace(e.getStackTrace());
	}

}
