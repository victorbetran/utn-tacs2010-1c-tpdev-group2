package org.utn.tacs.tp.group2.persistence.daos.exceptions;

import org.utn.tacs.tp.group2.domain.exceptions.PiezaException;

public class PiezaInexistenteException extends PiezaException {

	private static final long serialVersionUID = -911205952626430809L;

	public PiezaInexistenteException(String message) {
		super(message);
	}

}
