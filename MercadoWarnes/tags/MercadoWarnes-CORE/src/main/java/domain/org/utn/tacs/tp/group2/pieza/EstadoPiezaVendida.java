package org.utn.tacs.tp.group2.pieza;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaVendidaException;

@Entity
@DiscriminatorValue("VENDIDA")
public class EstadoPiezaVendida extends EstadoPieza {

	@Override public boolean isDisponible() {
		return false;
	}

	@Override public boolean isReservada() {
		return false;
	}

	@Override public boolean isVendida() {
		return true;
	}

	@Override public EstadoPieza gotoDisponible(Pieza pieza) {
		throw new PiezaVendidaException(pieza);
	}

	@Override public EstadoPieza gotoReservada(Pieza pieza) {
		throw new PiezaVendidaException(pieza);
	}

	@Override public EstadoPieza gotoVendida(Pieza pieza) {
		throw new PiezaVendidaException(pieza);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EstadoPiezaVendida){
			return false;
		}
		return ((EstadoPiezaVendida) obj).getId() == this.getId();
	}
	
	@Override
	protected long getId() {
		return 3;
	}
	
}
