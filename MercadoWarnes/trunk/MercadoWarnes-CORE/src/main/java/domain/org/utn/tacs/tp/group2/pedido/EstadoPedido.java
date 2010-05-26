package org.utn.tacs.tp.group2.pedido;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.utn.tacs.tp.group2.exceptions.pedido.PedidoCanceladoException;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoEfectivizadoException;

@Embeddable
public class EstadoPedido {

	private static EstadoPedido EN_CURSO = new EstadoPedido("En Curso");
	private static EstadoPedido EFECTIVO = new EstadoPedido("Efectivo");
	private static EstadoPedido CANCELADO  = new EstadoPedido("Cancelado");
	
	@Column(name="ESTADO")
	private String descripcion;
	
	public EstadoPedido() {}
	
	private EstadoPedido(String descripcion) {
		this.descripcion = descripcion;
	}

	public static EstadoPedido getEnCurso(){
		return EN_CURSO;
	}
	
	public static EstadoPedido getEfectivo(){
		return EFECTIVO;
	}
	
	public static EstadoPedido getCancelado(){
		return CANCELADO;
	}
	
	public void gotoEnCurso(Pedido pedido){
		if(isCancelado()) throw new PedidoCanceladoException(pedido);
		if(isEfectivo())  throw new PedidoEfectivizadoException(pedido);
		pedido.setEstado(EN_CURSO);
	}
	
	
	public void gotoCancelado(Pedido pedido){
		if(isEfectivo()) throw new PedidoEfectivizadoException(pedido);
		if(isCancelado()) throw new PedidoCanceladoException(pedido);
		pedido.setEstado(CANCELADO);
	}


	public void gotoEfectivo(Pedido pedido){
		if(isCancelado()) throw new PedidoCanceladoException(pedido);
		pedido.setEstado(EFECTIVO);
	}

	public boolean isCancelado(){
		return this == CANCELADO;
	}

	public boolean isEfectivo(){
		return this == EFECTIVO;
	}

	public boolean isEnCurso(){
		return this == EN_CURSO;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	@Override
	public String toString() {
		return this.descripcion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof EstadoPedido)) return false;
		if(obj == this) return true;
		EstadoPedido estado = (EstadoPedido)obj;
		return this.descripcion.equals(estado.descripcion);
	}
}
