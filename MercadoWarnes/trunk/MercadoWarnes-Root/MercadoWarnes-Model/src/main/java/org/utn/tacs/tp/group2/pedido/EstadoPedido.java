package org.utn.tacs.tp.group2.pedido;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * Clase abstracta que representa el estado de un pedido. Existen 3 estado posible: En Curso,
 * Cancelado, Efectivo.
 */
@Entity
@MappedSuperclass
@Table(name = "ESTADO_PEDIDO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO" ,discriminatorType=DiscriminatorType.STRING)
public abstract class EstadoPedido {

	/**
	 * Setea el estado del pedido a <b>EN CURSO</b>. Un Pedido puede transicionar al estado
	 * "En Curso" si no ha sido efectivizado o Cancelado.
	 */
	public abstract EstadoPedido gotoEnCurso(Pedido pedido);

	/**
	 * Setea el estado del pedido a <b>CANCELADO</b>. Un Pedido puede transicionar al estado
	 * "Cancelado" si no ha sido efectivizado.
	 */
	public abstract EstadoPedido gotoCancelado(Pedido pedido);

	/**
	 * Setea el estado del pedido a <b>EFECTIVO</b>. Un Pedido puede transicionar al estado
	 * "Efectivo" si no ha sido cancelado.
	 */
	public abstract EstadoPedido gotoEfectivo(Pedido pedido);

	/**
	 * Informa si el estado es cancelado.
	 */
	public abstract boolean isCancelado();

	/**
	 * Informa si el estado es efectivo.
	 */
	public abstract boolean isEfectivo();

	/**
	 * Informa si el estado es en curso.
	 */
	public abstract boolean isEnCurso();	
	

	// ********************************************
	// ** PUBLIC CLASS METHODS
	// ********************************************
	private static EstadoPedidoEnCurso estadoPedidoEnCurso = new EstadoPedidoEnCurso();
	/**
	 * Retorna un estado <b>EN CURSO</b>.
	 */
	public static EstadoPedido getEnCurso() {
		return estadoPedidoEnCurso;
	}

	private static EstadoPedidoCancelado estadoPedidoCancelado = new EstadoPedidoCancelado();
	/**
	 * Retorna un estado <b>CANCELADO</b>.
	 */
	public static EstadoPedido getCancelado() {
		return estadoPedidoCancelado;
	}

	private static EstadoPedidoEfectivo estadoPedidoEfectivo = new EstadoPedidoEfectivo();
	/**
	 * Retorna un estado <b>EFECTIVO</b>.
	 */
	public static EstadoPedido getEfectivo() {
		return estadoPedidoEfectivo;
	}
	
	//********************************************
	//** PERSISTENCE IMPLEMENTATION
	//********************************************
	
	@Id
	protected long id = getId();
	
	protected abstract long getId();
}
