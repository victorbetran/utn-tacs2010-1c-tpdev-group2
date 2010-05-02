package org.utn.tacs.tp.group2.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pedido.PedidoEfectivizadoException;

@Entity
@DiscriminatorValue("EFECTIVO")
public class EstadoPedidoEfectivo extends EstadoPedido{

	private String tipoEstado = "EFECTIVO";
	
	//********************************************
	//** PROTECTED CONSTRUCTOR
	//********************************************
	public EstadoPedidoEfectivo(Pedido pedido) {
		super(pedido);
	}

	
	//********************************************
	//** ESTADOPEDIDO OVERWRITTEN METHODS
	//********************************************
	@Override public boolean isCancelado() {
		return false;
	}

	@Override public boolean isEfectivo() {
		return true;
	}

	@Override public boolean isEnCurso() {
		return false;
	}

	@Override
	public EstadoPedido gotoCancelado() {
		throw new PedidoEfectivizadoException(this.pedido);
	}

	@Override public EstadoPedido gotoEfectivo() {
		throw new PedidoEfectivizadoException(this.pedido);
	}

	@Override
	public EstadoPedido gotoEnCurso() {
		throw new PedidoEfectivizadoException(this.pedido);
	}

	public String getTipoEstado() {
		return tipoEstado;
	}

}
