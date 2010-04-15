package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaNoReservadaException extends BusinessModelException {

	private static final long serialVersionUID = -805120885355552484L;

	public PiezaNoReservadaException(Pieza pieza) {
		super("No se puede vender la pieza '" + pieza.getCodigo()
				+ "' debido a que la misma no ha sido Reservada previamente.");
	}

}
