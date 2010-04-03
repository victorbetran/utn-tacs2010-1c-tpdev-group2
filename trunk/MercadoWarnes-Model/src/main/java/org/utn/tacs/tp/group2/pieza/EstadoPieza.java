package org.utn.tacs.tp.group2.pieza;





public class EstadoPieza {

	private static final String DISPONIBLE = "DISPONIBLE";
	private static final String RESERVADA = "RESERVADA";
	private static final String VENDIDA = "VENDIDA";	

	/**
	 * Estado de la pieza
	 */
	private String estado;

	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 */
	protected EstadoPieza() {}
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Setea el estado de la pieza a <b>DISPONIBLE</b>.
	 */
	public EstadoPieza setDisponible(){ 
		this.estado = DISPONIBLE; 
		return this;
	}
	
	/**
	 * Setea el estado de la pieza a <b>RESERVADA</b>.
	 */
	public EstadoPieza setReservada(){ 
		this.estado = RESERVADA; 
		return this;
	}
	
	/**
	 * Setea el estado de la pieza a <b>VENDIDA</b>.
	 */
	public EstadoPieza setVendida(){ 
		this.estado = VENDIDA; 
		return this;
	}
	
	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	/**
	 * Retorna un estado <b>DISPONIBLE</b>.
	 */
	public static EstadoPieza getEstadoDisponible() {
		return new EstadoPieza().setDisponible();
	}
	
	/**
	 * Retorna un estado <b>RESERVADA</b>.
	 */
	public static EstadoPieza getEstadoReservada() {
		return new EstadoPieza().setReservada();
	}
	
	/**
	 * Retorna un estado <b>VENDIDA</b>.
	 */
	public static EstadoPieza getEstadoVendida() {
		return new EstadoPieza().setVendida();
	}
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		if(!(obj instanceof EstadoPieza)){
			return false;
		}
		EstadoPieza estadoPieza = (EstadoPieza) obj;
		return estadoPieza.estado.equals(this.estado);
	}
}
