package org.utn.tacs.tp.group2.pieza;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaNoReservadaException;

@Entity
@DiscriminatorValue("DISPONIBLE")
public class EstadoPiezaDisponible extends EstadoPieza{

	
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
	public EstadoPieza gotoDisponible(Pieza pieza) {
		return this;
	}

	@Override
	public EstadoPieza gotoReservada(Pieza pieza) {
		return getEstadoReservada();
	}

	@Override
	public EstadoPieza gotoVendida(Pieza pieza) {
		throw new PiezaNoReservadaException(pieza);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EstadoPiezaDisponible){
			return false;
		}
		return ((EstadoPiezaDisponible) obj).getId() == this.getId();
	}

	@Override
	protected long getId() {
		return 1;
	}

}
