package org.utn.tacs.tp.group2.pieza;

import org.utn.tacs.tp.group2.exceptions.PiezaNoReservadaException;
import org.utn.tacs.tp.group2.exceptions.PiezaVendidaException;

/**
 * Al ser el estado de una pieza algo inherente a la pieza, no esta bueno que pueda accederse
 * desde el exterior, por lo que el estado es ahora una clase "privada", es decir, su scope
 * solo se mantiene dentro del package.
 */
class EstadoPieza {

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
	
	/**
	 * Pieza a la cual corresponde el estado.
	 */
	private Pieza pieza;

	
	//********************************************
	//** CONSTRUCTOR
	//********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 */
	protected EstadoPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Setea el estado de la pieza a <b>DISPONIBLE</b>.
	 */
	public EstadoPieza setDisponible(){ 
		if(this.estado == VENDIDA)
			throw new PiezaVendidaException(this.pieza);
		this.estado = DISPONIBLE; 
		return this;
	}
	
	/**
	 * Setea el estado de la pieza a <b>RESERVADA</b>.
	 */
	public EstadoPieza setReservada(){
		if(this.estado == VENDIDA)
			throw new PiezaVendidaException(this.pieza);
		this.estado = RESERVADA; 
		return this;
	}
	
	/**
	 * Setea el estado de la pieza a <b>VENDIDA</b>.
	 */
	public EstadoPieza setVendida(){ 
		if(this.estado == DISPONIBLE)
			throw new PiezaNoReservadaException(this.pieza);
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
	public static EstadoPieza getEstadoDisponibleFor(Pieza pieza) {
		return new EstadoPieza(pieza).setDisponible();
	}
	
	/**
	 * Retorna un estado <b>RESERVADA</b>.
	 */
	public static EstadoPieza getEstadoReservadaFor(Pieza pieza) {
		return new EstadoPieza(pieza).setReservada();
	}
	
	/**
	 * Retorna un estado <b>VENDIDA</b>.
	 */
	public static EstadoPieza getEstadoVendidaFor(Pieza pieza) {
		return new EstadoPieza(pieza).setVendida();
	}
	
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override
	public boolean equals(Object obj) {
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
