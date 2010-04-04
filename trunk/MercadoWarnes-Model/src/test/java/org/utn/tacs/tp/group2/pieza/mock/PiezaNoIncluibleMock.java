package org.utn.tacs.tp.group2.pieza.mock;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaNoIncluibleMock extends Pieza{

	@Override
	public boolean isDisponible() {
		return false;
	}
	
}
