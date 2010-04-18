package org.utn.tacs.tp.group2.pieza;

/**
 * Clase abstracta que representa el estado de una pieza. Existen 3
 * estado posible: Disponible, Reservada, Vendida.
 */
public abstract class EstadoPieza {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	protected Pieza pieza;
	protected String descripcion;
	
	private volatile int hashCode;
	

	//********************************************
	//** CONSTRUCTOR
	//********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 * @param descripcion 
	 * @param pieza 
	 */
	protected EstadoPieza(Pieza pieza, String descripcion) {
		this.descripcion = descripcion;
		this.pieza = pieza;
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Setea el estado de la pieza a <b>DISPONIBLE</b>.
	 * Una pieza no puede pasar a estar disponible si esta Vendida.
	 */
	public abstract EstadoPieza gotoDisponible();
	
	/**
	 * Setea el estado de la pieza a <b>RESERVADA</b>.
	 * Una pieza puede pasar a estar Reservada sólo si esta Disponible y no esta Vendida.
	 */
	public abstract EstadoPieza gotoReservada();
	
	/**
	 * Setea el estado de la pieza a <b>VENDIDA</b>.
	 * Una pieza puede venderse únicamente si se encuentra reservada.
	 */
	public abstract EstadoPieza gotoVendida();
	
	/**
	 * Informa si la pieza está disponible.
	 */
	public abstract boolean isDisponible();
	
	/**
	 * Informa si la pieza está reservada.
	 */	
	public abstract boolean isReservada();
	
	/**
	 * Informa si la pieza está vendida.
	 */	
	public abstract boolean isVendida();
	
	
	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	/**
	 * Retorna un estado <b>DISPONIBLE</b>.
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
	
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj == this){
			return true;
		}
		if(!(obj instanceof EstadoPieza)){
			return false;
		}
		EstadoPieza otherEstado = (EstadoPieza) obj;
		return this.descripcion.equals(otherEstado.descripcion);
	}

	@Override public String toString() {
		return this.descripcion;
	}
	
	@Override public int hashCode() {
		int result = this.hashCode;
		if(result == 0){
			result = 17;
			result = 31 * result * this.descripcion.hashCode();
			this.hashCode = result;
		}
		return result;
	}


}
