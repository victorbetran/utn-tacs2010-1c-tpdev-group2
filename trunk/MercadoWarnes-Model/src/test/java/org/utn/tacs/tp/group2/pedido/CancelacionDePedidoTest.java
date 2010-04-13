package org.utn.tacs.tp.group2.pedido;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.CancelarPedidoException;
import org.utn.tacs.tp.group2.exceptions.PedidoCanceladoException;
import org.utn.tacs.tp.group2.exceptions.PedidoEfectivizadoException;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class CancelacionDePedidoTest {
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	private Pedido pedido;
	
	
	//********************************************
	//** CONTEXT
	//********************************************
	@Before
	public void setUp(){
		this.pedido = new Pedido();
	}

	
	//********************************************
	//** TESTS
	//********************************************
	/**
	 * Cancela un pedido recientemente creado, es decir, un pedido <b>EN CURSO</b>
	 */
	@Test public void cancelarUnPedidoEnCurso(){
		this.pedido.cancelar();
		Assert.assertTrue("El pedido no se ha cancelado correctamente.",this.pedido.isCancelado());
		Assert.assertFalse("El pedido se ha cancelado pero aún contiene piezas.",this.pedido.tienePiezas());
	}
	
	/**
	 * Cancela un pedido que fue efectifizado, es decir, un pedido <b>EFECTIVO</b>.
	 */
	@Test (expected=PedidoEfectivizadoException.class)
	public void cancelarUnPedidoEfectivo(){
		this.pedido.addPieza(new Pieza("Z-456"));
		this.pedido.efectivizar();
		this.pedido.cancelar();
	}
	
	/**
	 * Cancela un pedido que había sido cancelado previamente, es decir, un pedido <b>CANCELADO</b>.
	 */
	@Test (expected=PedidoCanceladoException.class)
	public void cancelarUnPedidoCancelado(){
		this.pedido.cancelar();
		this.pedido.cancelar();
	}
}
