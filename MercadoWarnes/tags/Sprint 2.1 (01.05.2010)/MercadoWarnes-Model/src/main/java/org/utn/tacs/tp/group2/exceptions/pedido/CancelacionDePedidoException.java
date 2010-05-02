package org.utn.tacs.tp.group2.exceptions.pedido;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaException;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class CancelacionDePedidoException extends PedidoException {

	private static final long serialVersionUID = 3432013381170742600L;
	
	public CancelacionDePedidoException(Pedido pedido, PiezaException e) {
		super("Error al cancelar el pedido '" + pedido + "'.");
		this.setStackTrace(e.getStackTrace());
	}


}
