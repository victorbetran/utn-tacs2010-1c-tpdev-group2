package org.utn.tacs.tp.group2.pieza;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaVendidaException;

@Entity
@DiscriminatorValue("VENDIDA")
public class EstadoPiezaVendida extends EstadoPieza {

	//********************************************
	//** CONSTRUCTOR
	//********************************************
	public EstadoPiezaVendida(Pieza pieza) {
		super(pieza);
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

	@Override
	public boolean equals(Object obj) {
		return  obj instanceof EstadoPiezaVendida;
	}
	
}
