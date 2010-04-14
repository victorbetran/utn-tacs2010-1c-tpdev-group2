package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class VenderPiezaException extends BusinessModelException {

	private static final long serialVersionUID = 1150908331502920340L;

	public VenderPiezaException(Pieza pieza) {
		super("La pieza '" + pieza.getCodigo() + "' no puede venderse por estar " + pieza.getEstado() + ".");
	}

}
