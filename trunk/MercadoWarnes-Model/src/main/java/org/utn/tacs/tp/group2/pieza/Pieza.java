package org.utn.tacs.tp.group2.pieza;

import java.math.BigDecimal;

public class Pieza {
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	private EstadoPieza estado;
	private String id;
	private String codigo;
	private String descripcion;
	private CategoriaPieza categoria;
	private Auto autoOrigen;
	private BigDecimal precio;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	public Pieza() {
		this.estado = EstadoPieza.getDisponible();
		this.categoria = CategoriaPieza.getStandar();
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Establece a la pieza como Disponible.
	 */
	public void setDisponible() {
		this.estado.setDisponible(this);		
	}
	
	/**
	 * Establece a la pieza como Reservada.
	 */
	public void setReservada() {
		this.estado.setReservada(this);
	}
	
	/**
	 * Establece a la pieza como Vendida.
	 */
	public void setVendida() {
		this.estado.setVendida(this);		
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
		return this.estado.isReservada();
	}

	/**
	 * Determina si la pieza se encuentra disponible.
	 */
	public boolean isDisponible() {
		return this.estado.isDisponible();
	}
	
	/**
	 * Determina si la pieza se encuentra vendida.
	 */
	public boolean isVendida() {
		return this.estado.isVendida();
	}
	
	/**
	 * Determina si una pieza se encuentra en un estado particular.
	 */
	public boolean isInEstado(EstadoPieza estado) {
		return this.estado.equals(estado);
	}
	
	/**
	 * Determina si la pieza pertenece a una categoria.
	 */
	public boolean perteneceA(CategoriaPieza categoria){
		return this.categoria.equals(categoria);
	}
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public String toString() {
		return this.codigo;
	}
	
	@Override public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj == this){
			return true;
		}
		if(!(obj instanceof Pieza)){
			return false;
		}
		Pieza pieza = (Pieza) obj;
		return this.id.equals(pieza.id);
	}
	

	//********************************************
	//** GETTERS & SETTERS
	//********************************************
	public String getId() { 
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public CategoriaPieza getCategoria() {
		return this.categoria;
	}
	public Pieza setCategoria(CategoriaPieza categoria) {
		this.categoria = categoria;
		return this;
	}

	public String getCodigo() {
		return this.codigo;
	}
	public Pieza setCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	public Pieza setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public Auto getAutoOrigen() {
		return this.autoOrigen;
	}
	public Pieza setAutoOrigen(Auto autoOrigen) {
		this.autoOrigen = autoOrigen;
		return this;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}
	public Pieza setPrecio(BigDecimal precio) {
		this.precio = precio;
		return this;
	}
	
}
