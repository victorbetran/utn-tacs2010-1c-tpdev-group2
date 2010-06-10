package org.utn.tacs.tp.group2.daos.exceptions;

import org.utn.tacs.tp.group2.exceptions.pedido.PedidoException;

public class PedidoInexistenteException extends PedidoException {

	private static final long serialVersionUID = 910777447059749984L;

	public PedidoInexistenteException(String message) {
		super(message);
	}

}
