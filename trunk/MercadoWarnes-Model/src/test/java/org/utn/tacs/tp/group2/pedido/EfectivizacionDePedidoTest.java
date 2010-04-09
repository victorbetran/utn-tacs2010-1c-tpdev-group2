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
	
	@Test public void efectivizarPedido()
	{
		Pieza pieza1=new Pieza();		
		this.pedido.addPieza(pieza1);		
		this.pedido.efectivizar();
		Assert.assertTrue(this.pedido.isEfectivo());
	}
	
	@Test public void efectivizarPedidoConPiezasVendidas()
	{
		Pieza pieza1=new Pieza();
		Pieza pieza2=new Pieza();		
		this.pedido.addPieza(pieza1);
		this.pedido.addPieza(pieza2);
		this.pedido.efectivizar();
		Assert.assertTrue(pieza1.isVendida() && pieza2.isVendida());
	}
	
	@Test(expected=PedidoSinPiezasException.class) 
	public void efectivizarPedidoSinPiezas()
	{
		this.pedido.efectivizar();		
	}
	
	@Test(expected=PedidoEfectivizadoException.class) 
	public void efectivizarPedidoYaEfectivizado()
	{
		Pieza pieza1=new Pieza();		
		this.pedido.addPieza(pieza1);		
		this.pedido.efectivizar();
		this.pedido.efectivizar();	
	}

}
