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
	 * Una pieza que no fue reservada no puede ser vendida. Una pieza
	 * DISPONIBLE, implica que no est� RESERVADA.
	 */
	@Test(expected = PiezaNoReservadaException.class)
	public void venderUnaPiezaDisponible() {
		this.pieza.setVendida();
	}

	/**
	 * Una pieza s�lo puede venderse si fue reservada.
	 */
	@Test
	public void venderUnaPiezaReservada() {
		this.pieza.setReservada();
		this.pieza.setVendida();
		Assert.assertTrue(this.pieza.isVendida());
	}

	/**
	 * Vende varias piezas reservadas.
	 */
	@Test
	public void venderVariasPiezasReservadas() {
		for (Pieza pieza : listadoDePiezas) {
			pieza.setReservada();
			pieza.setVendida();
			Assert.assertTrue(pieza.isVendida());
		}
	}

	/**
	 * Vender una pieza ya vendida.
	 */
	@Test(expected = PiezaVendidaException.class)
	public void venderUnaPiezaYaVendida() {
		this.pieza.setReservada();
		this.pieza.setVendida();
		this.pieza.setVendida();
	}

	/**
	 * Una pieza s�lo puede reservarse si est� DISPONIBLE.
	 */
	@Test
	public void reservarUnaPiezaDisponible() {
		this.pieza.setReservada();
		Assert.assertTrue(this.pieza.isReservada());
	}

	/**
	 * Una pieza vendida no puede reservarse.
	 */
	@Test(expected = PiezaVendidaException.class)
	public void reservarUnaPiezaVendida() {
		Assert.assertTrue(this.pieza.isDisponible());
		this.pieza.setReservada();
		Assert.assertTrue(this.pieza.isReservada());
		this.pieza.setVendida();
		Assert.assertTrue(this.pieza.isVendida());
		this.pieza.setReservada();
	}

	/**
	 * Reservar varias piezas.
	 */
	@Test
	public void reservarVariasPiezas() {
		for (Pieza pieza : this.listadoDePiezas) {
			pieza.setReservada();
		}

		for (Pieza pieza : this.listadoDePiezas) {
			Assert.assertTrue(pieza.isReservada());
		}
	}

}
