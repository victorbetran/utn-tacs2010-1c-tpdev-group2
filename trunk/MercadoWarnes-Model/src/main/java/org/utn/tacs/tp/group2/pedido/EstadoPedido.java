package org.utn.tacs.tp.group2.pedido;

import org.utn.tacs.tp.group2.exceptions.PedidoException;

public class EstadoPedido {

	//********************************************
	//** CLASS ATRIBUTTES
	//********************************************
	private static final String EN_CURSO = "EN_CURSO";
	private static final String CANCELADO = "CANCELADO";
	private static final String EFECTIVO = "EFECTIVO";
	
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	/**
	 * Estado del pedido.
	 */
	private String estado;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 */
	protected EstadoPedido() {}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Setea el estado del pedido a <b>EN CURSO</b>.
	 */
	public EstadoPedido setEnCurso(){ 
		this.estado = EN_CURSO; 
		return this;
	}
	
	/**
	 * Setea el estado del pedido a <b>CANCELADO</b>.
	 */
	public EstadoPedido setCancelado(){ 
		if(this.estado == EFECTIVO) 
			throw new PedidoException("No se puede cancelar un pedido efectivizado.");
		if(this.estado == CANCELADO) 
			throw new PedidoException("El pedido ya ha sido cancelado, no puede volver a cancelarse.");		
		this.estado = CANCELADO; 
		return this;
	}
	
	/**
	 * Setea el estado del pedido a <b>EFECTIVO</b>.
	 */
	public EstadoPedido setEfectivo(){ 
		this.estado = EFECTIVO; 
		return this;
	}

	
	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	/**
	 * Retorna un estado <b>EN CURSO</b>.
	 */
	public static EstadoPedido getEstadoEnCurso() {
		return new EstadoPedido().setEnCurso();
	}
	
	/**
	 * Retorna un estado <b>CANCELADO</b>.
	 */
	public static EstadoPedido getEstadoCancelado() {
		return new EstadoPedido().setCancelado();
	}
	
	/**
	 * Retorna un estado <b>EFECTIVO</b>.
	 */
	public static EstadoPedido getEstadoEfectivo() {
		return new EstadoPedido().setEfectivo();
	}
	
	/**
	 * Informa si el estado es cancelado.
	 */
	public boolean isCancelado() {
		return this.estado.equals(CANCELADO);
	}
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		if(!(obj instanceof EstadoPedido)){ 
			return false;
		}
		EstadoPedido estadoPedido = (EstadoPedido) obj;
		return estadoPedido.estado.equals(this.estado);
	}

}
