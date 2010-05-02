package org.utn.tacs.tp.group2.domain.exceptions;

import org.utn.tacs.tp.group2.domain.Pieza;

public class PiezaVendidaException extends PiezaException {

	private static final long serialVersionUID = -6447688428066201333L;

	public PiezaVendidaException(Pieza pieza) {
		super("La pieza '" + pieza + "' se encuentra vendida.");
	}

}
