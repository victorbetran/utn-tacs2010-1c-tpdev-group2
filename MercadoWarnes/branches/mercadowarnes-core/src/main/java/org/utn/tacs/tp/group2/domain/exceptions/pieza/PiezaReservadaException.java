package org.utn.tacs.tp.group2.domain.exceptions.pieza;

import org.utn.tacs.tp.group2.domain.pieza.Pieza;

public class PiezaReservadaException extends PiezaException {

	private static final long serialVersionUID = 4947086890666905228L;

	public PiezaReservadaException(Pieza pieza) {
		super("La pieza '" + pieza + "' se encuentra reservada.");
	}

}
