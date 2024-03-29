package org.utn.tacs.tp.group2.pieza;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaNoReservadaException;

@Entity
@DiscriminatorValue("Disponible")
public class EstadoPiezaDisponible extends EstadoPieza{

	
	private String tipoEstado = "DISPONIBLE";
	//********************************************
	//** CONSTRUCTOR
	//********************************************
	public EstadoPiezaDisponible(Pieza pieza) {
		super(pieza);
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
	public EstadoPieza gotoDisponible() {
		return this;
	}

	@Override
	public EstadoPieza gotoReservada() {
		return new EstadoPiezaReservada(this.pieza);
	}

	@Override
	public EstadoPieza gotoVendida() {
		throw new PiezaNoReservadaException(this.pieza);
	}
	@Override
	public String getTipoEstado() {
		return tipoEstado;
	}

}
