package org.utn.tacs.tp.group2.exceptions.pieza;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaNoReservadaException extends PiezaException {

	private static final long serialVersionUID = -805120885355552484L;

	public PiezaNoReservadaException(Pieza pieza) {
		super("No se puede vender la pieza '" + pieza
				+ "' debido a que la misma no ha sido Reservada previamente.");
	}

}
