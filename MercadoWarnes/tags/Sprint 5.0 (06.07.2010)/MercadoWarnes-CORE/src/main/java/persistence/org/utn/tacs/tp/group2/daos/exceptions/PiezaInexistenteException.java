package org.utn.tacs.tp.group2.daos.exceptions;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaException;

public class PiezaInexistenteException extends PiezaException {

	private static final long serialVersionUID = -911205952626430809L;

	public PiezaInexistenteException(String message) {
		super(message);
	}

}
