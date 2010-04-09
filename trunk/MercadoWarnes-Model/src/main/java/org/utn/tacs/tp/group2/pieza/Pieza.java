package org.utn.tacs.tp.group2.pieza;

import java.math.BigDecimal;


public class Pieza {
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	/**
	 * Estado en que se encuentra la pieza.
	 */
	private EstadoPieza estado;
	
	/**
	 * Identificador de la pieza.
	 */
	@SuppressWarnings("unused")
	private String id = "1";
	
	/**
	 * Código único de la pieza.
	 */
	private String codigo = "Pieza";
	
	/**
	 * Descripcion de la pieza.
	 */
	@SuppressWarnings("unused")
	private String descripcion = "Pieza";
	
	/**
	 * Categoría de la pieza.
	 */
	@SuppressWarnings("unused")
	private CategoriaPieza categoria;
	
	/**
	 * Auto al cual pertenece la pieza.
	 */
	@SuppressWarnings("unused")
	private Auto autoOrigen;
	
	/**
	 * Precio de la Pieza.
	 */
	@SuppressWarnings("unused")
	private BigDecimal precio;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	public Pieza() {
		this.estado = EstadoPieza.getEstadoDisponibleFor(this);
		this.categoria = CategoriaPieza.getCategoriaStandard();
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
	
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public String toString() {
		return this.codigo;
	}
	
}
