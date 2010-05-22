package org.utn.tacs.tp.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EN_CURSO")
public class EstadoPedidoEnCurso extends EstadoPedido{
	
	@Override public boolean isCancelado() {
		return false;
	}

	@Override public boolean isEfectivo() {
		return false;
	}

	@Override public boolean isEnCurso() {
		return true;
	}

	@Override public EstadoPedido gotoCancelado(Pedido pedido) {
		return EstadoPedido.getCancelado();		
	}

	@Override public EstadoPedido gotoEfectivo(Pedido pedido) {
		return EstadoPedido.getEfectivo();
	}

	@Override public EstadoPedido gotoEnCurso(Pedido pedido) {
		return this;
	}

	@Override
	public String toString() {
		return "Estado En Curso";
	}
	
	@Override
	protected long getId() {
		return 3;
	}

}
