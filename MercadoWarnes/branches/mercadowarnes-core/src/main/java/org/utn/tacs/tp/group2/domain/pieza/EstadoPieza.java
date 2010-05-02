package org.utn.tacs.tp.group2.domain.pieza;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase abstracta que representa el estado de una pieza. Existen 3 estado posible: Disponible,
 * Reservada, Vendida.
 */

@Entity
@MappedSuperclass
@Table(name = "ESTADO_PIEZA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO" ,discriminatorType=DiscriminatorType.STRING)
public abstract class EstadoPieza {

	/**
	 * Setea el estado de la pieza a <b>DISPONIBLE</b>. Una pieza no puede pasar a estar disponible
	 * si esta Vendida.
	 */
	public abstract EstadoPieza gotoDisponible(Pieza pieza);

	/**
	 * Setea el estado de la pieza a <b>RESERVADA</b>. Una pieza puede pasar a estar Reservada s�lo
	 * si esta Disponible y no esta Vendida.
	 */
	public abstract EstadoPieza gotoReservada(Pieza pieza);

	/**
	 * Setea el estado de la pieza a <b>VENDIDA</b>. Una pieza puede venderse �nicamente si se
	 * encuentra reservada.
	 */
	public abstract EstadoPieza gotoVendida(Pieza pieza);

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
	
	// ********************************************
	// ** FACTORY METHODS
	// ********************************************

	@Transient
	private static EstadoPiezaDisponible estadoPiezaDisponible = new EstadoPiezaDisponible();
	/**
	 * Retorna un estado <b>DISPONIBLE</b>.
	 * 
	 * @param pieza
	 */
	public static EstadoPieza getEstadoDisponible() {
		return estadoPiezaDisponible;
	}

	@Transient
	private static EstadoPiezaReservada estadoPiezaReservada = new EstadoPiezaReservada();
	/**
	 * Retorna un estado <b>RESERVADA</b>.
	 */
	public static EstadoPieza getEstadoReservada() {
		return estadoPiezaReservada;
	}

	@Transient
	private static EstadoPiezaVendida estadoPiezaVendida = new EstadoPiezaVendida();
	/**
	 * Retorna un estado <b>VENDIDA</b>.
	 */
	public static EstadoPieza getEstadoVendida() {
		return estadoPiezaVendida;
	}

	//********************************************
	//** PERSISTENCE IMPLEMENTATION
	//********************************************
	
	@Id
	protected long id = getId();
	
	protected abstract long getId();
	
}
