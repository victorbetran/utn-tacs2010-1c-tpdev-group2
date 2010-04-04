package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class InclusionDePiezaNoPermitidaException extends BusinessModelException{

	private static final long serialVersionUID = 1L;

	public InclusionDePiezaNoPermitidaException(Pieza pieza) {
		super("No puede incluirse una pieza en estado: " + pieza.getEstado());
	}
	
}
