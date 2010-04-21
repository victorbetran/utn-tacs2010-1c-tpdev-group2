package org.utn.tacs.tp.group2.pieza;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.utn.tacs.tp.group2.utils.PersistentObject;

@Entity
@Table(name = "PIEZA")
public class Pieza extends PersistentObject {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	@Column(nullable = false, length = 10)
	private String codigo;

	// TODO: El estado de la pieza al final iba a conocer la pieza no? para mappearlo seria mas
	// feliz :D
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private EstadoPieza estado;

	@Column(nullable = false, length = 128)
	private String descripcion;

	@OneToOne(optional = false)
	private CategoriaPieza categoria;

	@ManyToOne(optional = false)
	private Auto autoOrigen;

	@Column(nullable = false)
	private BigDecimal precio;

	// TODO: Hay que decirle que esto NO VA en la tabla
	private volatile int hashCode;

	// ********************************************
	// ** PUBLIC CONSTRUCTOR
	// ********************************************
	public Pieza(String codigo) {
		super();
		this.estado = EstadoPieza.getDisponible(this);
		this.categoria = CategoriaPieza.getStandar();
		this.codigo = codigo;
	}

	// ********************************************
	// ** PUBLIC METHODS
	// ********************************************
	/**
	 * Establece a la pieza como Disponible.
	 */
	public Pieza disponibilizar() {
		this.estado = this.estado.gotoDisponible();
		return this;
	}

	/**
	 * Establece a la pieza como Reservada.
	 */
	public Pieza reservar() {
		this.estado = this.estado.gotoReservada();
		return this;
	}

	/**
	 * Establece a la pieza como Vendida.
	 */
	public Pieza vender() {
		this.estado = this.estado.gotoVendida();
		return this;
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
	public boolean perteneceA(CategoriaPieza categoria) {
		return this.categoria.equals(categoria);
	}

	// ********************************************
	// ** OVERWRITTEN METHODS
	// ********************************************
	@Override
	public String toString() {
		return this.codigo;
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
		return this.id.equals(pieza.id);
	}

	@Override
	public int hashCode() {
		int result = this.hashCode;
		if (result == 0) {
			result = 17;
			result = 31 * result * this.id.hashCode();
			this.hashCode = result;
		}
		return result;
	}

	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************
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
