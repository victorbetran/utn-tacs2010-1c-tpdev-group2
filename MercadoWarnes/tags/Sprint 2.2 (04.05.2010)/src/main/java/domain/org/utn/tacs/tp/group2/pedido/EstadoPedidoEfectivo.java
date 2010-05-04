package org.utn.tacs.tp.group2.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.utn.tacs.tp.group2.exceptions.pedido.PedidoEfectivizadoException;

@Entity
@DiscriminatorValue("EFECTIVO")
public class EstadoPedidoEfectivo extends EstadoPedido{


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
	public EstadoPedido gotoCancelado(Pedido pedido) {
		throw new PedidoEfectivizadoException(pedido);
	}

	@Override public EstadoPedido gotoEfectivo(Pedido pedido) {
		throw new PedidoEfectivizadoException(pedido);
	}

	@Override
	public EstadoPedido gotoEnCurso(Pedido pedido) {
		throw new PedidoEfectivizadoException(pedido);
	}

	@Override
	public String toString() {
		return "Estado Efectivo";
	}
	
	@Override
	protected long getId() {
		return 2;
	}


}
