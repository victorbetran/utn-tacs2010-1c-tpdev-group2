package org.utn.tacs.tp.group2.pedido;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.InclusionDePiezaNoPermitidaException;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.pieza.mock.PiezaIncluibleMock;
import org.utn.tacs.tp.group2.pieza.mock.PiezaNoIncluibleMock;

public class CreacionDePedidoTest {

	private Pieza piezaDisponible;
	private Pieza otraPiezaDisponible;
	private Pieza piezaNoDisponible;
	private Pedido pedido;
	
	@Before
	public void setUp(){
		piezaDisponible = new PiezaIncluibleMock();
		otraPiezaDisponible = new PiezaIncluibleMock();
		piezaNoDisponible = new PiezaNoIncluibleMock();
		
		pedido = new Pedido();
	}
	
	@Test(expected=InclusionDePiezaNoPermitidaException.class)
	public void agregarPiezaNoDisponible(){
		pedido.addPieza(piezaNoDisponible);
	}
	
	public void agregarPieza(){
		pedido.addPieza(piezaDisponible);
	
		List<Pieza> piezasDelPedido = pedido.getPiezas();
		
		Assert.assertTrue(piezasDelPedido.size() == 1);
		Assert.assertEquals(piezasDelPedido.get(0), piezaDisponible);
	}

	public void agregarPiezas(){
		pedido.addPieza(piezaDisponible);
		pedido.addPieza(otraPiezaDisponible);
	
		List<Pieza> piezasDelPedido = pedido.getPiezas();
		
		Assert.assertTrue(piezasDelPedido.size() == 2);
		Assert.assertTrue(piezasDelPedido.contains(piezaDisponible));
		Assert.assertTrue(piezasDelPedido.contains(otraPiezaDisponible));
	}
	
}
