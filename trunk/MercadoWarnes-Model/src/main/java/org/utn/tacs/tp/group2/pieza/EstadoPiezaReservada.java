package org.utn.tacs.tp.group2.pieza;

import org.utn.tacs.tp.group2.exceptions.PiezaReservadaException;

public class EstadoPiezaReservada extends EstadoPieza {

	//********************************************
	//** CONSTRUCTOR
	//********************************************
	public EstadoPiezaReservada(Pieza pieza) {
		super(pieza, "Reservada");
	}
	
	
	//********************************************
	//** ESTADO PIEZA METHODS
	//********************************************
	@Override public boolean isDisponible() {
		return false;
	}

	@Override public boolean isReservada() {
		return true;
	}

	@Override public boolean isVendida() {
		return false;
	}

	@Override public EstadoPieza toDisponible() {
		return new EstadoPiezaDisponible(this.pieza);
	}

	@Override
	public EstadoPieza toReservada() {
		throw new PiezaReservadaException(this.pieza);
	}

	@Override
	public EstadoPieza toVendida() {
		return new EstadoPiezaVendida(this.pieza);
	}

}
