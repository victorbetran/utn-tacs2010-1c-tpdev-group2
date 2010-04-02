package org.utn.tacs.tp.group2.pedido;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.PedidoException;

public class CancelarPedidoTest {

	private Pedido pedido;
	
	@Before
	public void setUp() throws Exception {
		this.pedido = new Pedido();
	}

	
	/**Cancela un pedido recien creado, es decir, un pedido <b>EN CURSO</b>.*/
	@Test public void cancelarUnPedidoEnCurso(){
		pedido.cancelar();
		Assert.assertTrue(this.pedido.getEstado().equals(EstadoPedido.getEstadoCancelado()));
	}

	
	/**Cancela un pedido que fue efectifizado, es decir, un pedido <b>EFECTIVO</b>.*/
	@Test (expected=PedidoException.class)
	public void cancelarUnPedidoEfectivo(){
		this.pedido.efectivizar();
		pedido.cancelar();
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
