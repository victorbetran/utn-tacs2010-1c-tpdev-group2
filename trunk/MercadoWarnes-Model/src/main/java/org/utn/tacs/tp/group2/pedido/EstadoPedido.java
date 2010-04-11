package org.utn.tacs.tp.group2.pedido;

import org.utn.tacs.tp.group2.exceptions.PedidoCanceladoException;
import org.utn.tacs.tp.group2.exceptions.PedidoEfectivizadoException;

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
	String estado;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 */
	protected EstadoPedido(String estado) {
		this.estado = estado;
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Setea el estado del pedido a <b>EN CURSO</b>.
	 */
	public EstadoPedido setEnCurso(Pedido pedido){ 
		if(this.estado.equals(EFECTIVO)) 
			throw new PedidoEfectivizadoException(pedido);
		if(this.estado.equals(CANCELADO))
			throw new PedidoCanceladoException(pedido);		
		this.estado = EN_CURSO; 
		return this;
	}
	
	/**
	 * Setea el estado del pedido a <b>CANCELADO</b>.
	 */
	public EstadoPedido setCancelado(Pedido pedido){ 
		if(this.estado.equals(EFECTIVO)) 
			throw new PedidoEfectivizadoException(pedido);
		if(this.estado.equals(CANCELADO))
			throw new PedidoCanceladoException(pedido);		
		this.estado = CANCELADO; 
		return this;
	}
	
	/**
	 * Setea el estado del pedido a <b>EFECTIVO</b>.
	 */
	public EstadoPedido setEfectivo(Pedido pedido){ 
		if(this.estado.equals(CANCELADO)) 
			throw new PedidoCanceladoException(pedido);	
		this.estado = EFECTIVO; 
		return this;
	}

	/**
	 * Informa si el estado es cancelado.
	 */
	public boolean isCancelado() {
		return this.estado.equals(CANCELADO);
	}
	
	/**
	 * Informa si el estado es efectivo.
	 */
	public boolean isEfectivo() {
		return this.estado.equals(EFECTIVO);
	}
	
	/**
	 * Informa si el estado es en curso.
	 */
	public boolean isEnCurso() {
		return this.estado.equals(EN_CURSO);
	}
	
	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	/**
	 * Retorna un estado <b>EN CURSO</b>.
	 */
	public static EstadoPedido getEnCurso() {
		return new EstadoPedido(EN_CURSO);
	}
	
	/**
	 * Retorna un estado <b>CANCELADO</b>.
	 */
	public static EstadoPedido getCancelado() {
		return new EstadoPedido(CANCELADO);
	}
	
	/**
	 * Retorna un estado <b>EFECTIVO</b>.
	 */
	public static EstadoPedido getEfectivo() {
		return new EstadoPedido(EFECTIVO);
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
		if(!(obj instanceof EstadoPedido)){ 
			return false;
		}
		EstadoPedido estadoPedido = (EstadoPedido) obj;
		return estadoPedido.estado.equals(this.estado);
	}




}
