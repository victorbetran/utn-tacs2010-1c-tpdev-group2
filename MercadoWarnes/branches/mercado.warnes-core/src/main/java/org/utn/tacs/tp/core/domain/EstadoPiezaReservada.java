package org.utn.tacs.tp.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.core.exceptions.PiezaReservadaException;

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
		return ((EstadoPiezaReservada) obj).getId() == this.getId();
	}
	
	@Override
	protected long getId() {
		return 2;
	}
	
}
