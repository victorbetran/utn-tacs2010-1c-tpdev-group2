package org.utn.tacs.tp.core.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.utn.tacs.tp.core.utils.Logueador;
import org.utn.tacs.tp.core.utils.PersistentObject;

@Entity
public class Pieza extends PersistentObject {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	@Column(length = 10)
	private String codigo;

	@OneToOne(cascade = CascadeType.ALL)
	private EstadoPieza estado;

	@Column(length = 128)
	private String descripcion;

	@Column(length = 16)	
	private String categoria;

	@ManyToOne(cascade = CascadeType.ALL)
	private Auto autoOrigen;

	@Column
	private BigDecimal precio;

	// ********************************************
	// ** PUBLIC CONSTRUCTOR
	// ********************************************
	public Pieza(String codigo) {
		this.estado = EstadoPieza.getEstadoDisponible();
		this.codigo = codigo;
		Logueador.getInstance().loguearDebug("Se creo la pieza: " + this.toString());
	}

	public Pieza(){
		
	}
	
	// ********************************************
	// ** PUBLIC METHODS
	// ********************************************
	/**
	 * Establece a la pieza como Disponible.
	 */
	public Pieza disponibilizar() {
		this.setEstado(this.estado.gotoDisponible(this));
		Logueador.getInstance().loguearDebug("Se puso disponible la pieza: " + this.toString());
		return this;
	}

	/**
	 * Establece a la pieza como Reservada.
	 */
	public Pieza reservar() {
		this.setEstado(this.estado.gotoReservada(this));
		Logueador.getInstance().loguearDebug("Se reservo la pieza: " + this.toString());
		return this;
	}

	/**
	 * Establece a la pieza como Vendida.
	 */
	public Pieza vender() {
		this.setEstado(this.estado.gotoVendida(this));
		Logueador.getInstance().loguearDebug("Se vendio la pieza: " + this.toString());
		return this;
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
	public boolean perteneceA(String categoria) {
		return this.categoria.equals(categoria);
	}

	// ********************************************
	// ** OVERWRITTEN METHODS
	// ********************************************
	@Override
	public String toString() {
		return this.getId().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Pieza)) {
			return false;
		}
		Pieza pieza = (Pieza) obj;
		return this.getId().equals(pieza.getId());
	}

	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************
	public String getCategoria() {
		return this.categoria;
	}

	public Pieza setCategoria(String categoria) {
		this.categoria = categoria;
		return this;
	}
//	public CategoriaPieza getCategoria() {
//		return this.categoria;
//	}
//
//	public Pieza setCategoria(CategoriaPieza categoria) {
//		this.categoria = categoria;
//		return this;
//	}

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
	public EstadoPieza getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoPieza estado) {
		this.estado = estado;
	}
	
}
