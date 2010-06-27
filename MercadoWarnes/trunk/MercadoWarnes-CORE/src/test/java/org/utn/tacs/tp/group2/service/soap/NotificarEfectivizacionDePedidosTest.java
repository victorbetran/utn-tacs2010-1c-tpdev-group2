package org.utn.tacs.tp.group2.service.soap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;


public class NotificarEfectivizacionDePedidosTest {

	private Pedido pedido;
	private NotificadorDePedidosAdapterMock obs = new NotificadorDePedidosAdapterMock();
	
	
	@Before
	public void setUp(){
		Pieza aPieza = new Pieza("CodigoPieza",10,Moneda.Dolares);
		Pieza otherPieza = new Pieza("CodigoPieza",10,Moneda.Dolares);
		this.pedido = Pedido.create();
		this.pedido.addPieza(aPieza);
		this.pedido.addPieza(otherPieza);
		this.pedido.acceptEfectivizacionObserver(this.obs);
	}
	
	@Test
	public void notificacionExitosaDePedido(){
		this.pedido.efectivizar();
		Assert.assertTrue(this.obs.isNotificated(this.pedido));
	}
	
}
