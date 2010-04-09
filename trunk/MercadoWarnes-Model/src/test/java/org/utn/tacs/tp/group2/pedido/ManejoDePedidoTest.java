package org.utn.tacs.tp.group2.pedido;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class ManejoDePedidoTest {

	private Pedido pedido;
	private Pieza pieza1;
	private Pieza pieza2;
	private Pieza pieza3;
	private List<Pieza> listadoPiezasParaAgregar;
	
	@Before
	public void setUp(){
		this.pedido = new Pedido();
		
		this.pieza1 = new Pieza();
		this.pieza2 = new Pieza();
		this.pieza3 = new Pieza();
		
		this.listadoPiezasParaAgregar = new ArrayList<Pieza>();
		this.listadoPiezasParaAgregar.add(this.pieza1);
		this.listadoPiezasParaAgregar.add(this.pieza2);
		this.listadoPiezasParaAgregar.add(this.pieza3);
	}

	/**
	 * Agrega piezas a un pedido.
	 */
	@Test
	public void agregarPiezasAUnPedido(){
		this.pedido.addPieza(this.pieza1);
		this.pedido.addPieza(this.pieza2);
		this.pedido.addPieza(this.pieza3);
		
		List<Pieza> listaPiezas = this.pedido.getPiezas();
		
		Assert.assertTrue(listaPiezas.size() == 3);
		Assert.assertTrue(listaPiezas.contains(this.pieza1));
		Assert.assertTrue(listaPiezas.contains(this.pieza2));
		Assert.assertTrue(listaPiezas.contains(this.pieza3));		
	}
	
	/**
	 * Agrega una lista de piezas a un pedido.
	 */
	@Test
	public void agregarUnaListaDePiezasAUnPedido(){
		this.pedido.addPiezas(this.listadoPiezasParaAgregar);
		
		List<Pieza> listaPiezasDelPedido = this.pedido.getPiezas();
		
		for(Pieza pieza : listaPiezasDelPedido){
			Assert.assertTrue(this.listadoPiezasParaAgregar.contains(pieza));
		}
	}

}
