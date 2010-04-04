package org.utn.tacs.tp.group2.pieza;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.PiezaNoReservadaException;
import org.utn.tacs.tp.group2.exceptions.PiezaVendidaException;

public class PiezaTest {

	private Pieza pieza;
	private List<Pieza> listadoDePiezas;
	
	@Before
	public void setUp() {
		this.pieza = new Pieza();		
		this.listadoDePiezas = new ArrayList<Pieza>();
		
		//Agrego 10 piezas a la lista de piezas
		for (int i = 0; i < 10; i++) {
			this.listadoDePiezas.add(new Pieza());
		}
	}
	
	
	/**
	 * Reservar varias piezas.
	 */
	@Test
	public void reservarVariasPiezas(){
		for (Pieza pieza : this.listadoDePiezas) {
			pieza.setReservada();
		}
		
		for (Pieza pieza : this.listadoDePiezas) {
			Assert.assertTrue(pieza.isReservada());
		}
	}

	/**
	 * Una pieza que no fue reservada no puede ser vendida.
	 */
	@Test(expected=PiezaNoReservadaException.class)
	public void venderUnaPiezaDisponible() {
		Assert.assertTrue(this.pieza.isDisponible());
		this.pieza.setVendida();
	}
	
	/**
	 * Una pieza sólo puede venderse si fue vendida.
	 */
	@Test public void venderUnaPiezaReservada() {
		this.pieza.setReservada();
		Assert.assertTrue(this.pieza.isReservada());
		this.pieza.setVendida();
	}
	
	/**
	 * Una pieza sólo puede reservarse si esta disponible.
	 */
	@Test public void reservarUnaPiezaDisponible() {
		Assert.assertTrue(this.pieza.isDisponible());
		this.pieza.setReservada();
		Assert.assertTrue(this.pieza.isReservada());
	}
	
	/**
	 * Una pieza vendida no puede reservarse.
	 */
	@Test(expected=PiezaVendidaException.class)
	public void reservarUnaPiezaVendida() {
		Assert.assertTrue(this.pieza.isDisponible());
		this.pieza.setReservada();
		Assert.assertTrue(this.pieza.isReservada());
		this.pieza.setVendida();
		Assert.assertTrue(this.pieza.isVendida());
		this.pieza.setReservada();
	}

}
