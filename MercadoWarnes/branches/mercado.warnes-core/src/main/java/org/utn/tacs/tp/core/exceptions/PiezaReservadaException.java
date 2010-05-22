package org.utn.tacs.tp.core.exceptions;

import org.utn.tacs.tp.core.domain.Pieza;

public class PiezaReservadaException extends PiezaException {

	private static final long serialVersionUID = 4947086890666905228L;

	public PiezaReservadaException(Pieza pieza) {
		super("La pieza '" + pieza + "' se encuentra reservada.");
	}

}
