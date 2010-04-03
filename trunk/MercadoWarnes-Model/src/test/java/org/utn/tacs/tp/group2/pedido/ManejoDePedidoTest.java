package org.utn.tacs.tp.group2.pedido;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.PedidoException;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class ManejoDePedidoTest {

	private Pedido pedido;
	private Pieza pieza1;
	private Pieza pieza2;
	private Pieza pieza3;
	private List<Pieza> listaPiezas;
	
	@Before
	public void setUp(){
		this.pedido = new Pedido();
		
		this.pieza1 = new Pieza();
		this.pieza2 = new Pieza();
		this.pieza3 = new Pieza();
		
		this.listaPiezas = new ArrayList<Pieza>();
		this.listaPiezas.add(this.pieza1);
		this.listaPiezas.add(this.pieza2);
		this.listaPiezas.add(this.pieza3);
	}

	/**
	 * Cancela un pedido recientemente creado, es decir, un pedido <b>EN CURSO</b>
	 */
	@Test public void cancelarUnPedidoEnCurso(){
		pedido.cancelar();
		Assert.assertTrue(this.pedido.getEstado().equals(EstadoPedido.getEstadoCancelado()));
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
	
	/**
	 * Agrega piezas a un pedido.
	 */
	@Test
	public void agregarPiezasAUnPedido(){
		this.pedido.agregarPieza(this.pieza1);
		this.pedido.agregarPieza(this.pieza2);
		this.pedido.agregarPieza(this.pieza3);
		
		List<Pieza> listaPiezas = this.pedido.getPiezas();
		
		Assert.assertTrue(listaPiezas.contains(this.pieza1));
		Assert.assertTrue(listaPiezas.contains(this.pieza2));
		Assert.assertTrue(listaPiezas.contains(this.pieza3));		
	}
	
	/**
	 * Agrega una lista de piezas a un pedido.
	 */
	@Test
	public void agregarUnaListaDePiezasAUnPedido(){
		this.pedido.setPiezas(this.listaPiezas);
		
		List<Pieza> listaPiezasDelPedido = this.pedido.getPiezas();
		
		//Recorro la lista de piezas del pedido y verifico que c/pieza también este
		//en la listaPiezas del setUp() de los test.
		for(Pieza pieza : listaPiezasDelPedido){
			Assert.assertTrue(this.listaPiezas.contains(pieza));
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
