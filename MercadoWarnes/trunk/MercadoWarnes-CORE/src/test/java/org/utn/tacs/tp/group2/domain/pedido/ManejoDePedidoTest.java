package org.utn.tacs.tp.group2.domain.pedido;


import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pedido.PedidoBuilder;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class ManejoDePedidoTest {

	private Pedido pedido;
	private Pieza pieza1;
	private Pieza pieza2;
	private Pieza pieza3;
	
	@Before
	public void setUp(){
		this.pedido = new PedidoBuilder().Build();
		
		this.pieza1 = new Pieza("A-123",new BigDecimal(30),Moneda.Dolares);
		this.pieza2 = new Pieza("B-369",new BigDecimal(30),Moneda.Pesos);
		this.pieza3 = new Pieza("C-147",new BigDecimal(30),Moneda.Pesos);
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
