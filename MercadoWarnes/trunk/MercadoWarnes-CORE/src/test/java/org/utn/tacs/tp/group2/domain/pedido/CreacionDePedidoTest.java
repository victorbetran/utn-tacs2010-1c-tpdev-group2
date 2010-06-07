package org.utn.tacs.tp.group2.domain.pedido;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.pieza.PiezaReservadaException;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pedido.PedidoBuilder;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class CreacionDePedidoTest {

	private Pieza piezaDisponible;
	private Pieza otraPiezaDisponible;
	private Pieza piezaNoDisponible;
	private Pedido pedido;
	
	@Before
	public void setUp(){
		
		this.piezaDisponible = new Pieza("W-894",30,Moneda.Dolares);
		this.otraPiezaDisponible = new Pieza("K-666",30,Moneda.Dolares);
		this.piezaNoDisponible = new Pieza("N-000",30,Moneda.Dolares).reservar();
		this.pedido = new PedidoBuilder().Build();
	}
	
	
	/**
	 * Controla que a una pieza no disponible no se le permita ser agregada a un pedido.
	 */
	@Test(expected=PiezaReservadaException.class)
	public void agregarPiezaNoDisponible(){
		this.pedido.addPieza(this.piezaNoDisponible);
	}
	
	/**
	 * Controla la adición de una pieza a un pedido.
	 */
	@Test
	public void agregarPieza(){
		this.pedido.addPieza(this.piezaDisponible);
	
		List<Pieza> piezasDelPedido = this.pedido.getPiezas();
		
		Assert.assertEquals("El pedido contiene mas piezas de las que le fueron asignadas.",this.pedido.cantidadDePiezasAsignadas(),1);
		Assert.assertEquals("El pedido contiene una pieza diferente a la que le fue agregada.",piezasDelPedido.get(0), this.piezaDisponible);
	}

	/**
	 * Controla la adición de piezas a un pedido.
	 */
	@Test
	public void agregarPiezas(){
		this.pedido.addPieza(this.piezaDisponible);
		this.pedido.addPieza(this.otraPiezaDisponible);
	
		Assert.assertEquals("El pedido contiene mas piezas de las que le fueron asignadas.",this.pedido.cantidadDePiezasAsignadas(),2);
		Assert.assertTrue("El pedido no contiene una pieza que le fue agregada.",this.pedido.contienePieza(this.piezaDisponible));
		Assert.assertTrue("El pedido no contiene una pieza que le fue agregada.",this.pedido.contienePieza(this.otraPiezaDisponible));
	}
	
}
