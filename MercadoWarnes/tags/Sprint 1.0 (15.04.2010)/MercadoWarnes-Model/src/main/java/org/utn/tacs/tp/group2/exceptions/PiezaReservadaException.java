package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaReservadaException extends BusinessModelException {

	private static final long serialVersionUID = 4947086890666905228L;

	public PiezaReservadaException(Pieza pieza) {
		super("La pieza '" + pieza + "' se encuentra reservada.");
	}

}
