package org.utn.tacs.tp.group2.pieza;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PiezaTest {

	private Pieza pieza;
	private List<Pieza> listaPiezas;
	
	@Before
	public void setUp() {
		this.pieza = new Pieza();		
		listaPiezas = new ArrayList<Pieza>();
		
		//Agrego 10 piezas a la lista de piezas
		for (int i = 0; i < 10; i++) {
			listaPiezas.add(new Pieza());
		}
	}
	
	/**
	 * Reserva una pieza.
	 */
	@Test
	public void reservarUnaPieza(){
		pieza.setReservada();		
		Assert.assertTrue(this.pieza.getEstado().equals(EstadoPieza.getEstadoReservada()));
	}
	
	/**
	 * Reservar varias piezas.
	 */
	@Test
	public void reservarVariasPiezas(){
		for (Pieza pieza : listaPiezas) {
			pieza.setReservada();
		}
		
		for (Pieza pieza : listaPiezas) {
			Assert.assertTrue(pieza.getEstado().equals(EstadoPieza.getEstadoReservada()));
		}
	}

	@After
	public void tearDown() {
	}

}
