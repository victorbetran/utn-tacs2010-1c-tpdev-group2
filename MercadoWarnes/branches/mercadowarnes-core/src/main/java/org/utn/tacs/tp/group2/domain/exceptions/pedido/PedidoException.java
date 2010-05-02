package org.utn.tacs.tp.group2.domain.exceptions.pedido;

import org.utn.tacs.tp.group2.domain.exceptions.BusinessModelException;


public abstract class PedidoException extends BusinessModelException{

	private static final long serialVersionUID = -2316551861116403765L;

	public PedidoException(String message) {
		super(message);
	}

}
