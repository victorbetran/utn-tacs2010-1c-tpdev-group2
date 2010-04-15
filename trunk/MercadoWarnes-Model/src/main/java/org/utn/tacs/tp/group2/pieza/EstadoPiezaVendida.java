package org.utn.tacs.tp.group2.pieza;

import org.utn.tacs.tp.group2.exceptions.PiezaVendidaException;

public class EstadoPiezaVendida extends EstadoPieza {

	//********************************************
	//** CONSTRUCTOR
	//********************************************
	public EstadoPiezaVendida(Pieza pieza) {
		super(pieza, "Vendida");
	}
	
	
	//********************************************
	//** ESTADO PIEZA METHODS
	//********************************************
	@Override public boolean isDisponible() {
		return false;
	}

	@Override public boolean isReservada() {
		return false;
	}

	@Override public boolean isVendida() {
		return true;
	}

	@Override public EstadoPieza toDisponible() {
		throw new PiezaVendidaException(this.pieza);
	}

	@Override public EstadoPieza toReservada() {
		throw new PiezaVendidaException(this.pieza);
	}

	@Override public EstadoPieza toVendida() {
		throw new PiezaVendidaException(this.pieza);
	}

}
