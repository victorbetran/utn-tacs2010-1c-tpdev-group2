package org.utn.tacs.tp.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.core.exceptions.PedidoCanceladoException;

@Entity
@DiscriminatorValue("CANCELADO")
public class EstadoPedidoCancelado extends EstadoPedido{

	//********************************************
	//** ESTADOPEDIDO OVERWRITTEN METHODS
	//********************************************
	
	@Override public boolean isCancelado() {
		return true;
	}

	@Override public boolean isEfectivo() {
		return false;
	}

	@Override public boolean isEnCurso() {
		return false;
	}

	@Override
	public EstadoPedido gotoCancelado(Pedido pedido) {
		throw new PedidoCanceladoException(pedido);
	}

	@Override
	public EstadoPedido gotoEfectivo(Pedido pedido) {
		throw new PedidoCanceladoException(pedido);
	}

	@Override
	public EstadoPedido gotoEnCurso(Pedido pedido) {
		throw new PedidoCanceladoException(pedido);
	}

	@Override
	public String toString() {
		return "Estado Cancelado";
	}
	
	@Override
	protected long getId() {
		return 1;
	}

}
