package org.utn.tacs.tp.group2.exceptions.pedido;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaException;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class EfectivizacionDePedidoException extends PedidoException {

	private static final long serialVersionUID = 7428027625586568079L;

	public EfectivizacionDePedidoException(Pedido pedido, PiezaException e) {
		super("Error al efectivizar el pedido '" + pedido + "'.");
		this.setStackTrace(e.getStackTrace());
	}

}
