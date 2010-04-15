package org.utn.tacs.tp.group2.pieza;

import org.utn.tacs.tp.group2.exceptions.PiezaNoReservadaException;

public class EstadoPiezaDisponible extends EstadoPieza{

	//********************************************
	//** CONSTRUCTOR
	//********************************************
	public EstadoPiezaDisponible(Pieza pieza) {
		super(pieza, "Disponible");
	}
	
	
	//********************************************
	//** ESTADO PIEZA METHODS
	//********************************************
	@Override public boolean isDisponible() {
		return true;
	}

	@Override public boolean isReservada() {
		return false;
	}

	@Override public boolean isVendida() {
		return false;
	}

	@Override
	public EstadoPieza setDisponible() {
		return this;
	}

	@Override
	public EstadoPieza setReservada() {
		return new EstadoPiezaReservada(this.pieza);
	}

	@Override
	public EstadoPieza setVendida() {
		throw new PiezaNoReservadaException(this.pieza);
	}

}
