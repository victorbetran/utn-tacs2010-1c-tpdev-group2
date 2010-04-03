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
		return estado;
	}



}
