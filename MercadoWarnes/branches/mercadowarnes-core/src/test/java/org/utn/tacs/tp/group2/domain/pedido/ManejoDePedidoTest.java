package org.utn.tacs.tp.group2.domain.pedido;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.domain.pieza.Pedido;
import org.utn.tacs.tp.group2.domain.pieza.Pieza;

public class ManejoDePedidoTest {

	private Pedido pedido;
	private Pieza pieza1;
	private Pieza pieza2;
	private Pieza pieza3;
	
	@Before
	public void setUp(){
		this.pedido = new Pedido();
		
		this.pieza1 = new Pieza("A-123");
		this.pieza2 = new Pieza("B-369");
		this.pieza3 = new Pieza("C-147");
	}

	/**
	 * Agrega piezas a un pedido.
	 */
	@Test
	public void agregarPiezasAUnPedido(){
		this.pedido.addPieza(this.pieza1);
		this.pedido.addPieza(this.pieza2);
		this.pedido.addPieza(this.pieza3);
		
		Assert.assertEquals("El pedido tiene mas piezas de las que le fueron agregadas",this.pedido.cantidadDePiezasAsignadas(),3);
		Assert.assertTrue("La pieza fue agregada al pedido pero el mismo no la contiene.",this.pedido.contienePieza(this.pieza1));
		Assert.assertTrue("La pieza fue agregada al pedido pero el mismo no la contiene.",this.pedido.contienePieza(this.pieza2));
		Assert.assertTrue("La pieza fue agregada al pedido pero el mismo no la contiene.",this.pedido.contienePieza(this.pieza3));
	}
	
	/**
	 * Agrega una pieza al pedido y la efectiviza.
	 */
	@Test
	public void efectivizarPedido(){
		this.pedido.addPieza(this.pieza1);
		this.pedido.efectivizar();
		Assert.assertTrue("El pedido no se ha efectivizado correctamente.",this.pedido.isEfectivo());
	}
	
}
