package org.utn.tacs.tp.group2.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EN_CURSO")
public class EstadoPedidoEnCurso extends EstadoPedido{
	
	private String tipoEstado = "EN_CURSO";

	//********************************************
	//** PROTECTED CONSTRUCTOR
	//********************************************
	protected EstadoPedidoEnCurso(Pedido pedido) {
		super(pedido);
	}

	//********************************************
	//** ESTADOPEDIDO OVERWRITTEN METHODS
	//********************************************
	@Override public boolean isCancelado() {
		return false;
	}

	@Override public boolean isEfectivo() {
		return false;
	}

	@Override public boolean isEnCurso() {
		return true;
	}

	@Override public EstadoPedido gotoCancelado() {
		return new EstadoPedidoCancelado(this.pedido);		
	}

	@Override public EstadoPedido gotoEfectivo() {
		return new EstadoPedidoEfectivo(this.pedido);
	}

	@Override public EstadoPedido gotoEnCurso() {
		return this;
	}

	public String getTipoEstado() {
		return tipoEstado;
	}

}
