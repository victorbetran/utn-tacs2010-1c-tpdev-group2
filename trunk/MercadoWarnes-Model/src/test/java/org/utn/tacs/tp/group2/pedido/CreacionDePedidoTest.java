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
	
	/**
	 * Controla que a una pieza no disponible no se le permita ser agregada a un pedido.
	 */
	@Test(expected=InclusionDePiezaNoPermitidaException.class)
	public void agregarPiezaNoDisponible(){
		pedido.addPieza(piezaNoDisponible);
	}
	
	/**
	 * Controla la adición de una pieza a un pedido.
	 */
	public void agregarPieza(){
		pedido.addPieza(piezaDisponible);
	
		List<Pieza> piezasDelPedido = pedido.getPiezas();
		
		Assert.assertEquals("El pedido contiene mas piezas de las que le fueron asignadas.",pedido.cantidadDePiezasAsignadas(),1);
		Assert.assertEquals("El pedido contiene una pieza diferente a la que le fue agregada.",piezasDelPedido.get(0), piezaDisponible);
	}

	/**
	 * Controla la adición de piezas a un pedido.
	 */
	public void agregarPiezas(){
		pedido.addPieza(piezaDisponible);
		pedido.addPieza(otraPiezaDisponible);
	
		Assert.assertEquals("El pedido contiene mas piezas de las que le fueron asignadas.",pedido.cantidadDePiezasAsignadas(),2);
		Assert.assertTrue("El pedido no contiene una pieza que le fue agregada.",pedido.contienePieza(piezaDisponible));
		Assert.assertTrue("El pedido no contiene una pieza que le fue agregada.",pedido.contienePieza(otraPiezaDisponible));
	}
	
}
