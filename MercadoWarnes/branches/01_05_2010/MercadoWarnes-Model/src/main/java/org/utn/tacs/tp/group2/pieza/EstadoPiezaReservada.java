package org.utn.tacs.tp.group2.pieza;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaReservadaException;

@Entity
@DiscriminatorValue("RESERVADA")
public class EstadoPiezaReservada extends EstadoPieza {

	@Override
	public boolean isDisponible() {
		return false;
	}

	@Override
	public boolean isReservada() {
		return true;
	}

	@Override
	public boolean isVendida() {
		return false;
	}

	@Override
	public EstadoPieza gotoDisponible(Pieza pieza) {
		return getEstadoDisponible();
	}

	@Override
	public EstadoPieza gotoReservada(Pieza pieza) {
		throw new PiezaReservadaException(pieza);
	}

	@Override
	public EstadoPieza gotoVendida(Pieza pieza) {
		return getEstadoVendida();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EstadoPiezaReservada){
			return false;
		}
		return ((EstadoPiezaReservada) obj).getId().equals(this.getId());
	}

	//********************************************
	//** PERSISTENCE IMPLEMENTATION
	//********************************************

	@Override
	protected Long getIdValue() {
		return ID_ESTADO_RESERVADA;
	}
	
}
