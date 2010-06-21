package org.utn.tacs.tp.group2.exceptions.services;

import org.utn.tacs.tp.group2.daos.exceptions.PiezaInexistenteException;

public class ComposicionPedidoInvalida extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ComposicionPedidoInvalida(PiezaInexistenteException e) {
		super(e);
	}

}
