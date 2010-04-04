package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaNoReservadaException extends BusinessModelException{

	private static final long serialVersionUID = 4672400655167673213L;

	public PiezaNoReservadaException(Pieza pieza) {
		super("La pieza '" + pieza + "' no ha sido reservada.");
	}
}
