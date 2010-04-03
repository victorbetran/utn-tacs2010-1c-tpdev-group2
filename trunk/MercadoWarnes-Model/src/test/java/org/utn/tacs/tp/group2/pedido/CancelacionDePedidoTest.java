package org.utn.tacs.tp.group2.pedido;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.PedidoException;

public class CancelacionDePedidoTest {
	
	private Pedido pedido;
	
	@Before	public void setUp(){
		this.pedido = new Pedido();
	}

	/**
	 * Cancela un pedido recientemente creado, es decir, un pedido <b>EN CURSO</b>
	 */
	@Test public void cancelarUnPedidoEnCurso(){
		this.pedido.cancelar();
		
		//Al cancelar un pedido el estado del mismo debe ser cancelado.
		Assert.assertTrue(this.pedido.getEstado().equals(EstadoPedido.getEstadoCancelado()));
		
		//El pedido no debe poseer piezas al ser cancelado.
		Assert.assertEquals(0, this.pedido.getPiezas().size());
	}

	/**
	 * Cancela un pedido que fue efectifizado, es decir, un pedido <b>EFECTIVO</b>.
	 */
	@Test (expected=PedidoException.class)
	public void cancelarUnPedidoEfectivo(){
		this.pedido.efectivizar();
		this.pedido.cancelar();
	}
	
	/**
	 * Cancela un pedido que había sido cancelado previamente, es decir, un pedido <b>CANCELADO</b>.
	 */
	@Test (expected=PedidoException.class)
	public void cancelarUnPedidoCancelado(){
		this.pedido.cancelar();
		this.pedido.cancelar();
	}
}
