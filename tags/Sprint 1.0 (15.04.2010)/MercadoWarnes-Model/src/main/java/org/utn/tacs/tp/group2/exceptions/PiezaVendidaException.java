package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaVendidaException extends BusinessModelException {

	private static final long serialVersionUID = -6447688428066201333L;

	public PiezaVendidaException(Pieza pieza) {
		super("La pieza '" + pieza + "' ya ha sido vendida.");
	}

}
