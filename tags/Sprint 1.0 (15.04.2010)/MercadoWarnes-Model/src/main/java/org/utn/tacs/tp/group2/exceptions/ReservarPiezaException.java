package org.utn.tacs.tp.group2.exceptions;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class ReservarPiezaException extends BusinessModelException {

	private static final long serialVersionUID = 3210781688901526054L;

	public ReservarPiezaException(Pieza pieza) {
		super("La pieza '" + pieza.getCodigo() + "' no puede reservarse por estar " + pieza.getEstado() + ".");
	}

}
