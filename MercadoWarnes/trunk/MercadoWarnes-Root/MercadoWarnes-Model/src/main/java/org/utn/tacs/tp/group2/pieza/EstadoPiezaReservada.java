package org.utn.tacs.tp.group2.pieza;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaReservadaException;

@Entity
@DiscriminatorValue("Reservada")
public class EstadoPiezaReservada extends EstadoPieza {

	private String tipoEstado = "RESERVADA";
	//********************************************
	//** CONSTRUCTOR
	//********************************************
	public EstadoPiezaReservada(Pieza pieza) {
		super(pieza);
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

	@Override public EstadoPieza gotoDisponible() {
		return new EstadoPiezaDisponible(this.pieza);
	}

	@Override
	public EstadoPieza gotoReservada() {
		throw new PiezaReservadaException(this.pieza);
	}

	@Override
	public EstadoPieza gotoVendida() {
		return new EstadoPiezaVendida(this.pieza);
	}
	@Override
	public String getTipoEstado() {
		return tipoEstado;
	}

}
