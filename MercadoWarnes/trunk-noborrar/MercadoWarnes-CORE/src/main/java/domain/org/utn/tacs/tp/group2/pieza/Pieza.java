package org.utn.tacs.tp.group2.pieza;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.utn.tacs.tp.group2.log.TheLogger;
import org.utn.tacs.tp.group2.persistence.PersistentObject;

@Entity
@Table(name = "PIEZA")
public class Pieza extends PersistentObject {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	@Column(length = 10)
	private String codigo;

	@Embedded
	private EstadoPieza estado;

	@Column(length = 128)
	private String descripcion;

	@Column(length = 16)	
	private String categoria;

	@ManyToOne(cascade = CascadeType.ALL)
	private Auto autoOrigen;

	@Embedded
	private Precio precio;

	// ********************************************
	// ** PUBLIC CONSTRUCTOR
	// ********************************************
	public Pieza(String codigo,double precio,Moneda moneda) {
		this.estado = EstadoPieza.getEstadoDisponible();
		this.codigo = codigo;
		this.precio=new Precio(moneda,precio);
		TheLogger.getConsoleLogger().debug("Se crea la pieza:{}", this);
	}
	
	/**
	 * Este metodo crea una pieza con un autoOrigen y una categoria "DEFAULT". Lo necesita el
	 * converter de pedidosApi. El codigo, estado y precio se deben setear manualmente.
	 * 
	 * @return una pieza
	 */
	public static Pieza createDefault() {
		Pieza pieza = new Pieza();
		pieza.setEstado(EstadoPieza.getEstadoDisponible());
		pieza.setAutoOrigen(Auto.createAuto("DEFAULT", "DEFAULT", 2010, new Date()));
		pieza.setCategoria("DEFAULT");
		TheLogger.getConsoleLogger().debug("Se crea la pieza:{}", pieza);
		return pieza;
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
		TheLogger.getConsoleLogger().debug("Se disponibiliza la pieza:{}", this);
		return this;
	}

	/**
	 * Establece a la pieza como Reservada.
	 */
	public Pieza reservar() {
		this.setEstado(this.estado.gotoReservada(this));
		TheLogger.getConsoleLogger().debug("Se reserva la pieza:{}", this);
		return this;
	}

	/**
	 * Establece a la pieza como Vendida.
	 */
	public Pieza vender() {
		this.setEstado(this.estado.gotoVendida(this));
		TheLogger.getConsoleLogger().debug("Se vende la pieza:{}", this);
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


	public EstadoPieza getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoPieza estado) {
		this.estado = estado;
	}
	
	public double getPrecioEn(Moneda moneda) {
		return this.precio.getValorEn(moneda);
	}
	
	public Precio getPrecio(){
		return this.precio;
	}


	/**
	 * Necesario en el converter de JMS.
	 * @param precio
	 */
	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	
}
