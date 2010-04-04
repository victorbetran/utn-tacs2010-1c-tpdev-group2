package org.utn.tacs.tp.group2.pieza;

public class Pieza {
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	private EstadoPieza estado;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	public Pieza() {
		this.estado = EstadoPieza.getEstadoDisponible();
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Establece a la pieza como Disponible.
	 */
	public void setDisponible() {
		this.estado.setDisponible();		
	}
	
	/**
	 * Establece a la pieza como Reservada.
	 */
	public void setReservada() {
		this.estado.setReservada();
	}
	
	/**
	 * Establece a la pieza como Vendida.
	 */
	public void setVendida() {
		this.estado.setVendida();		
	}

	/**
	 * Devuelve el estado de la pieza.
	 */
	public EstadoPieza getEstado() {
		return this.estado;
	}
	
	/**
	 * Determina si la pieza se encuentra reservada.
	 */
	public boolean isReservada() {
		return this.getEstado().isReservada();
	}

	/**
	 * Determina si la pieza se encuentra disponible.
	 */
	public boolean isDisponible() {
		return this.getEstado().isDisponible();
	}
	
	/**
	 * Determina si la pieza se encuentra vendida.
	 */
	public boolean isVendida() {
		return this.getEstado().isVendida();
	}

}
