package org.utn.tacs.tp.group2.pedido;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.PedidoEfectivizadoException;
import org.utn.tacs.tp.group2.exceptions.PedidoSinPiezasException;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class EfectivizacionDePedidoTest {

	private Pedido pedido;
	private Pieza unaPieza;
	private Pieza otraPieza;
	
	@Before
	public void setUp() throws Exception {
		this.pedido = new Pedido();
		unaPieza = new Pieza("P-987");
		otraPieza = new Pieza("6-985");
	}

	/**
	 * Chequea que el estado de un pedido cambie a EFECTIVO.
	 */
	@Test
	public void efectivizarPedido() {
		this.pedido.addPieza(unaPieza);
		this.pedido.efectivizar();
		Assert.assertTrue("No se ha efectivizado correctamente el pedido.",this.pedido.isEfectivo());
	}

	/**
	 * Verifica que las piezas de un pedido queden VENDIDAS ante la
	 * efectivización del pedido.
	 */
	@Test
	public void efectivizarPedidoVerificandoPiezasVendidas() {
		this.pedido.addPieza(unaPieza);
		this.pedido.addPieza(otraPieza);
		this.pedido.efectivizar();
		Assert.assertTrue(unaPieza.isVendida() && otraPieza.isVendida());
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
		this.pedido.addPieza(unaPieza);
		this.pedido.efectivizar();
		this.pedido.efectivizar();
	}

}
