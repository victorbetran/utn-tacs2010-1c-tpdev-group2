package org.utn.tacs.tp.group2.pedido;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.PedidoEfectivizadoException;
import org.utn.tacs.tp.group2.exceptions.PedidoSinPiezasException;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class EfectivizacionDePedidoTest {

	private Pedido pedido;
	
	@Before
	public void setUp() throws Exception {
		this.pedido = new Pedido();
	}

	/**
	 * Chequea que el estado de un pedido cambie a EFECTIVO.
	 */
	@Test
	public void efectivizarPedido() {
		Pieza pieza1 = new Pieza();
		this.pedido.addPieza(pieza1);
		this.pedido.efectivizar();
		Assert.assertTrue(this.pedido.isEfectivo());
	}

	/**
	 * Verifica que las piezas de un pedido queden VENDIDAS ante la
	 * efectivización del pedido.
	 */
	@Test
	public void efectivizarPedidoVerificandoPiezasVendidas() {
		Pieza pieza1 = new Pieza();
		Pieza pieza2 = new Pieza();
		this.pedido.addPieza(pieza1);
		this.pedido.addPieza(pieza2);
		this.pedido.efectivizar();
		Assert.assertTrue(pieza1.isVendida() && pieza2.isVendida());
	}

	/**
	 * Intenta efectivizar un pedido que no tiene agregadas piezas, por lo que
	 * tira una Exception (PedidoSinPiezasException).
	 */
	@Test(expected = PedidoSinPiezasException.class)
	public void efectivizarPedidoSinPiezas() {
		this.pedido.efectivizar();
	}

	/**
	 * Intenta efectivizar un pedido que fue previamente efectivizado, por lo
	 * que tira una Exception (PedidoEfectivizadoException).
	 */
	@Test(expected = PedidoEfectivizadoException.class)
	public void efectivizarPedidoYaEfectivizado() {
		Pieza pieza1 = new Pieza();
		this.pedido.addPieza(pieza1);
		this.pedido.efectivizar();
		this.pedido.efectivizar();
	}

}
