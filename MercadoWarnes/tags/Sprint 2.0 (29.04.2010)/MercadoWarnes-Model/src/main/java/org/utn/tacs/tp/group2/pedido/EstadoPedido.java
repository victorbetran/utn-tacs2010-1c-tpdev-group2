package org.utn.tacs.tp.group2.pedido;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.utn.tacs.tp.group2.utils.PersistentObject;

/**
 * Clase abstracta que representa el estado de un pedido. Existen 3 estado posible: En Curso,
 * Cancelado, Efectivo.
 */
@Entity
@Table(name = "ESTADO_PEDIDO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO" ,discriminatorType=DiscriminatorType.STRING)
public abstract class EstadoPedido extends PersistentObject {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	@OneToOne
	protected Pedido pedido;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	// ********************************************
	// ** PROTECTED CONSTRUCTOR
	// ********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 */
	protected EstadoPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public EstadoPedido() {
	}

	// ********************************************
	// ** PUBLIC ABSTRACT METHODS
	// ********************************************
	/**
	 * Setea el estado del pedido a <b>EN CURSO</b>. Un Pedido puede transicionar al estado
	 * "En Curso" si no ha sido efectivizado o Cancelado.
	 */
	public abstract EstadoPedido gotoEnCurso();

	/**
	 * Setea el estado del pedido a <b>CANCELADO</b>. Un Pedido puede transicionar al estado
	 * "Cancelado" si no ha sido efectivizado.
	 */
	public abstract EstadoPedido gotoCancelado();

	/**
	 * Setea el estado del pedido a <b>EFECTIVO</b>. Un Pedido puede transicionar al estado
	 * "Efectivo" si no ha sido cancelado.
	 */
	public abstract EstadoPedido gotoEfectivo();

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
	/**
	 * Retorna un estado <b>EN CURSO</b>.
	 */
	public static EstadoPedido getEnCurso(Pedido pedido) {
		return new EstadoPedidoEnCurso(pedido);
	}

	/**
	 * Retorna un estado <b>CANCELADO</b>.
	 */
	public static EstadoPedido getCancelado(Pedido pedido) {
		return new EstadoPedidoCancelado(pedido);
	}

	/**
	 * Retorna un estado <b>EFECTIVO</b>.
	 */
	public static EstadoPedido getEfectivo(Pedido pedido) {
		return new EstadoPedidoEfectivo(pedido);
	}
	
	public abstract String getTipoEstado();

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
		if (!(obj instanceof EstadoPedido)) {
			return false;
		}
		EstadoPedido otherEstado = (EstadoPedido) obj;
		return (otherEstado.getId().equals(this.getId()));
	}
//
//	@Override
//	public int hashCode() {
//		int result = this.hashCode;
//		if (result == 0) {
//			result = 17;
//			result = 31 * result * this.descripcion.hashCode();
//			this.hashCode = result;
//		}
//		return result;
//	}

	@Override
	public String toString() {
		return this.getId().toString();
	}

}
