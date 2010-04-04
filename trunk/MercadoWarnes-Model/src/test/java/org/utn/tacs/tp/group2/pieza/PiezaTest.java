package org.utn.tacs.tp.group2.pieza;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PiezaTest {

	private Pieza pieza;
	private List<Pieza> listadoDePiezas;
	
	@Before
	public void setUp() {
		this.pieza = new Pieza();		
		listadoDePiezas = new ArrayList<Pieza>();
		
		//Agrego 10 piezas a la lista de piezas
		for (int i = 0; i < 10; i++) {
			listadoDePiezas.add(new Pieza());
		}
	}
	
	/**
	 * Reserva una pieza.
	 */
	@Test
	public void reservarUnaPieza(){
		pieza.setReservada();
		Assert.assertTrue(this.pieza.isReservada());
	}

	/**
	 * Pone como vendida una pieza y luego verifica que no esté reservada.
	 */
	@Test
	public void controlarPiezaVendidaNoEsteReservada(){
		pieza.setVendida();
		Assert.assertTrue(!this.pieza.isReservada());
	}
	
	/**
	 * Reservar varias piezas.
	 */
	@Test
	public void reservarVariasPiezas(){
		for (Pieza pieza : listadoDePiezas) {
			pieza.setReservada();
		}
		
		for (Pieza pieza : listadoDePiezas) {
			Assert.assertTrue(pieza.isReservada());
		}
	}

	@After
	public void tearDown() {
	}

}
