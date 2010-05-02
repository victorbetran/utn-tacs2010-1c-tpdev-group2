package org.utn.tacs.tp.group2.persistence.daos.exceptions;

import org.utn.tacs.tp.group2.domain.exceptions.pedido.PedidoException;

public class PedidoInexistenteException extends PedidoException {

	private static final long serialVersionUID = 910777447059749984L;

	public PedidoInexistenteException(String message) {
		super(message);
	}

}
