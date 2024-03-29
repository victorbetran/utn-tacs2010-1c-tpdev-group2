package org.utn.tacs.tp.group2.pieza;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.utn.tacs.tp.group2.utils.PersistentObject;

/**
 * Clase abstracta que representa el estado de una pieza. Existen 3 estado posible: Disponible,
 * Reservada, Vendida.
 */
@Entity
@Table(name = "ESTADO_PIEZA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO" ,discriminatorType=DiscriminatorType.STRING)
public abstract class EstadoPieza extends PersistentObject {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	@OneToOne(optional = false, mappedBy="estado")
	protected Pieza pieza;

	// ********************************************
	// ** CONSTRUCTOR
	// ********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 * 
	 * @param descripcion
	 * @param pieza
	 */
	protected EstadoPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	// ********************************************
	// ** PUBLIC METHODS
	// ********************************************
	/**
	 * Setea el estado de la pieza a <b>DISPONIBLE</b>. Una pieza no puede pasar a estar disponible
	 * si esta Vendida.
	 */
	public abstract EstadoPieza gotoDisponible();

	/**
	 * Setea el estado de la pieza a <b>RESERVADA</b>. Una pieza puede pasar a estar Reservada s�lo
	 * si esta Disponible y no esta Vendida.
	 */
	public abstract EstadoPieza gotoReservada();

	/**
	 * Setea el estado de la pieza a <b>VENDIDA</b>. Una pieza puede venderse �nicamente si se
	 * encuentra reservada.
	 */
	public abstract EstadoPieza gotoVendida();

	/**
	 * Informa si la pieza est� disponible.
	 */
	public abstract boolean isDisponible();

	/**
	 * Informa si la pieza est� reservada.
	 */
	public abstract boolean isReservada();

	/**
	 * Informa si la pieza est� vendida.
	 */
	public abstract boolean isVendida();
	
	public abstract String getTipoEstado();

	// ********************************************
	// ** PUBLIC CLASS METHODS
	// ********************************************
	/**
	 * Retorna un estado <b>DISPONIBLE</b>.
	 * 
	 * @param pieza
	 */
	public static EstadoPieza getDisponible(Pieza pieza) {
		return new EstadoPiezaDisponible(pieza);
	}

	/**
	 * Retorna un estado <b>RESERVADA</b>.
	 */
	public static EstadoPieza getReservada(Pieza pieza) {
		return new EstadoPiezaReservada(pieza);
	}

	/**
	 * Retorna un estado <b>VENDIDA</b>.
	 */
	public static EstadoPieza getVendida(Pieza pieza) {
		return new EstadoPiezaVendida(pieza);
	}

	// ********************************************
	// ** OVERWRITTEN METHODS
	// ********************************************
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
//		if (!(obj instanceof EstadoPieza)) {
//			return false;
//		}
		EstadoPieza otherEstado = (EstadoPieza) obj;
		return this.getId().equals(otherEstado.getId());
	}

}
