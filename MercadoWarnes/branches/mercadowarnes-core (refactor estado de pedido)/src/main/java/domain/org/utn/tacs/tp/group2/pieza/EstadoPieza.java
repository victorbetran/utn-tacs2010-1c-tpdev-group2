package org.utn.tacs.tp.group2.pieza;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.utn.tacs.tp.group2.exceptions.pieza.PiezaNoReservadaException;
import org.utn.tacs.tp.group2.exceptions.pieza.PiezaReservadaException;
import org.utn.tacs.tp.group2.exceptions.pieza.PiezaVendidaException;

/**
 * Clase abstracta que representa el estado de una pieza. Existen 3 estado posible: Disponible,
 * Reservada, Vendida.
 */

@Embeddable
public class EstadoPieza {

	private static EstadoPieza DISPONIBLE = new EstadoPieza("Disponible");
	private static EstadoPieza RESERVADA = new EstadoPieza("Reservada");
	private static EstadoPieza VENDIDA  = new EstadoPieza("Vendida");
	
	@Column(name="ESTADO")
	private String descripcion;
	
	public EstadoPieza() {}
	
	private EstadoPieza(String descripcion) {
		this.descripcion = descripcion;
	}
	
	// ********************************************
	// ** FACTORY METHODS
	// ********************************************

	public static EstadoPieza getEstadoDisponible() { return DISPONIBLE; }

	public static EstadoPieza getEstadoReservada() { return RESERVADA; }

	public static EstadoPieza getEstadoVendida() { return VENDIDA; }
	
	
	
	/**
	 * Setea el estado de la pieza a <b>DISPONIBLE</b>. Una pieza no puede pasar a estar disponible
	 * si esta Vendida.
	 */
	public EstadoPieza gotoDisponible(Pieza pieza){
		if(isVendida()) throw new PiezaVendidaException(pieza);
		return DISPONIBLE;
	}
	
	
	/**
	 * Setea el estado de la pieza a <b>RESERVADA</b>. Una pieza puede pasar a estar Reservada s�lo
	 * si esta Disponible y no esta Vendida.
	 */
	public EstadoPieza gotoReservada(Pieza pieza){
		if(isVendida()) throw new PiezaVendidaException(pieza);
		if(isReservada()) throw new PiezaReservadaException(pieza);
		return RESERVADA;
	}

	/**
	 * Setea el estado de la pieza a <b>VENDIDA</b>. Una pieza puede venderse �nicamente si se
	 * encuentra reservada.
	 */
	public EstadoPieza gotoVendida(Pieza pieza){
		if(isDisponible()) throw new PiezaNoReservadaException(pieza);
		if(isVendida()) throw new PiezaVendidaException(pieza);
		return VENDIDA;
	}

	/**
	 * Informa si la pieza est� disponible.
	 */
	public boolean isDisponible(){
		return this == DISPONIBLE;
	}

	/**
	 * Informa si la pieza est� reservada.
	 */
	public boolean isReservada(){
		return this == RESERVADA;
	}

	/**
	 * Informa si la pieza est� vendida.
	 */
	public boolean isVendida(){
		return this == VENDIDA;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
