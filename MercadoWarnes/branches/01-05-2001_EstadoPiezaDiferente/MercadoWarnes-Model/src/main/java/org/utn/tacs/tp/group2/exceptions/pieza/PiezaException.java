package org.utn.tacs.tp.group2.exceptions.pieza;

import org.utn.tacs.tp.group2.exceptions.BusinessModelException;

public abstract class PiezaException extends BusinessModelException {

	private static final long serialVersionUID = 5359604334177939036L;

	public PiezaException(String message) {
		super(message);
	}

}
