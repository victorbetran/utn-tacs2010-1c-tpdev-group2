package org.utn.tacs.tp.group2.pedido;

import org.utn.tacs.tp.group2.exceptions.pedido.PedidoCanceladoException;

public class EstadoPedidoCancelado extends EstadoPedido{

	//********************************************
	//** PROTECTED CONSTRUCTOR
	//********************************************
	public EstadoPedidoCancelado(Pedido pedido) {
		super(pedido, "Cancelado");
	}

	
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
	public EstadoPedido gotoCancelado() {
		throw new PedidoCanceladoException(this.pedido);
	}

	@Override
	public EstadoPedido gotoEfectivo() {
		throw new PedidoCanceladoException(this.pedido);
	}

	@Override
	public EstadoPedido gotoEnCurso() {
		throw new PedidoCanceladoException(this.pedido);
	}

}
