package org.utn.tacs.tp.group2.pedido;

import org.utn.tacs.tp.group2.exceptions.pedido.PedidoEfectivizadoException;

public class EstadoPedidoEfectivo extends EstadoPedido{

	//********************************************
	//** PROTECTED CONSTRUCTOR
	//********************************************
	public EstadoPedidoEfectivo(Pedido pedido) {
		super(pedido, "Efectivo");
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

}
