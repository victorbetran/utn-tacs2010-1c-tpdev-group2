package org.utn.tacs.tp.group2.domain.exceptions.pieza;

import org.utn.tacs.tp.group2.domain.exceptions.BusinessModelException;


public abstract class PiezaException extends BusinessModelException {

	private static final long serialVersionUID = 5359604334177939036L;

	public PiezaException(String message) {
		super(message);
	}

}
