package org.utn.tacs.tp.group2.pieza;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaVendidaException;

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

	@Override public EstadoPieza gotoDisponible() {
		throw new PiezaVendidaException(this.pieza);
	}

	@Override public EstadoPieza gotoReservada() {
		throw new PiezaVendidaException(this.pieza);
	}

	@Override public EstadoPieza gotoVendida() {
		throw new PiezaVendidaException(this.pieza);
	}

}
