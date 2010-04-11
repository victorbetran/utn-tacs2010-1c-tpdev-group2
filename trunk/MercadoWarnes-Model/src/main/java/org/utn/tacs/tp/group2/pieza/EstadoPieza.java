package org.utn.tacs.tp.group2.pieza;

import org.utn.tacs.tp.group2.exceptions.PiezaNoReservadaException;
import org.utn.tacs.tp.group2.exceptions.PiezaVendidaException;

public class EstadoPieza {

	//********************************************
	//** CLASS VARIABLES
	//********************************************
	private static final String DISPONIBLE = "DISPONIBLE";
	private static final String RESERVADA = "RESERVADA";
	private static final String VENDIDA = "VENDIDA";	

	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	/**
	 * Estado de la pieza
	 */
	private String estado;
	

	//********************************************
	//** CONSTRUCTOR
	//********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 */
	protected EstadoPieza(String estado) {
		this.estado = estado;
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Setea el estado de la pieza a <b>DISPONIBLE</b>.
	 */
	public EstadoPieza setDisponible(Pieza pieza){ 
		if(this.estado == VENDIDA)
			throw new PiezaVendidaException(pieza);
		this.estado = DISPONIBLE; 
		return this;
	}
	
	/**
	 * Setea el estado de la pieza a <b>RESERVADA</b>.
	 */
	public EstadoPieza setReservada(Pieza pieza){
		if(this.estado == VENDIDA)
			throw new PiezaVendidaException(pieza);
		this.estado = RESERVADA; 
		return this;
	}
	
	/**
	 * Setea el estado de la pieza a <b>VENDIDA</b>.
	 */
	public EstadoPieza setVendida(Pieza pieza){ 
		if(this.estado == DISPONIBLE)
			throw new PiezaNoReservadaException(pieza);
		if(this.estado == VENDIDA)
			throw new PiezaVendidaException(pieza);
		this.estado = VENDIDA; 
		return this;
	}
	
	/**
	 * Informa si la pieza está disponible.
	 */
	public boolean isDisponible() {
		return this.estado.equals(DISPONIBLE);
	}
	
	/**
	 * Informa si la pieza está reservada.
	 */	
	public boolean isReservada() {
		return this.estado.equals(RESERVADA);
	}
	
	/**
	 * Informa si la pieza está vendida.
	 */	
	public boolean isVendida() {
		return this.estado.equals(VENDIDA);
	}
	
	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	/**
	 * Retorna un estado <b>DISPONIBLE</b>.
	 */
	public static EstadoPieza getDisponible() {
		return new EstadoPieza(DISPONIBLE);
	}
	
	/**
	 * Retorna un estado <b>RESERVADA</b>.
	 */
	public static EstadoPieza getReservada() {
		return new EstadoPieza(RESERVADA);
	}
	
	/**
	 * Retorna un estado <b>VENDIDA</b>.
	 */
	public static EstadoPieza getVendida() {
		return new EstadoPieza(VENDIDA);
	}
	
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this){
			return true;
		}
		if(!(obj instanceof EstadoPieza)){
			return false;
		}
		EstadoPieza estadoPieza = (EstadoPieza) obj;
		return estadoPieza.estado.equals(this.estado);
	}

	@Override public String toString() {
		return this.estado;
	}



}
