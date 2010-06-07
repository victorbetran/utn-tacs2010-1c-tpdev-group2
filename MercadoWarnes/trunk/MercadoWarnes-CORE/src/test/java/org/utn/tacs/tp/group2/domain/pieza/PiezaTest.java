package org.utn.tacs.tp.group2.domain.pieza;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.exceptions.pieza.PiezaNoReservadaException;
import org.utn.tacs.tp.group2.exceptions.pieza.PiezaVendidaException;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.pieza.Precio;

public class PiezaTest {

	private Pieza pieza;
	private List<Pieza> listadoDePiezas;
	
	@Before
	public void setUp() {
		this.pieza = new Pieza("L-963",40,Moneda.Pesos);		
		this.listadoDePiezas = new ArrayList<Pieza>();
		
		//Agrego 10 piezas a la lista de piezas
		for (int i = 0; i < 10; i++) {
			this.listadoDePiezas.add(new Pieza("V-546",50,Moneda.Dolares));
		}
	}
	

	/**
	 * Una pieza que no fue reservada no puede ser vendida. Una pieza
	 * DISPONIBLE, implica que no está RESERVADA.
	 */
	@Test(expected = PiezaNoReservadaException.class)
	public void venderUnaPiezaDisponible() {
		this.pieza.vender();
	}

	/**
	 * Una pieza sólo puede venderse si fue reservada.
	 */
	@Test
	public void venderUnaPiezaReservada() {
		this.pieza.reservar();
		this.pieza.vender();
		Assert.assertTrue("La pieza no ha sido correctamente vendida.",this.pieza.isVendida());
	}

	/**
	 * Vende varias piezas reservadas.
	 */
	@Test
	public void venderVariasPiezasReservadas() {
		for (Pieza pieza : this.listadoDePiezas) {
			pieza.reservar();
			pieza.vender();
			Assert.assertTrue("La pieza no ha sido correctamente vendida.",pieza.isVendida());
		}
	}

	/**
	 * Vender una pieza ya vendida.
	 */
	@Test(expected = PiezaVendidaException.class)
	public void venderUnaPiezaYaVendida() {
		this.pieza.reservar();
		this.pieza.vender();
		this.pieza.vender();
	}

	/**
	 * Una pieza sólo puede reservarse si está DISPONIBLE.
	 */
	@Test
	public void reservarUnaPiezaDisponible() {
		this.pieza.reservar();
		Assert.assertTrue("La pieza no ha sido correctamente reservada.",this.pieza.isReservada());
	}
	
	/**
	 * Reservar varias piezas.
	 */
	@Test
	public void reservarVariasPiezas() {
		for (Pieza pieza : this.listadoDePiezas) {
			pieza.reservar();
		}
		
		for (Pieza pieza : this.listadoDePiezas) {
			Assert.assertTrue("La pieza no ha sido correctamente reservada.",pieza.isReservada());
		}
	}

	/**
	 * Una pieza vendida no puede reservarse.
	 */
	@Test(expected = PiezaVendidaException.class)
	public void reservarUnaPiezaVendida() {
		this.pieza.reservar();
		this.pieza.vender();
		this.pieza.reservar();
	}
	
	/**
	 * Obtener el valor de la pieza en pesos con precio en pesos
	 */
	public void obtenerValorDePiezaEnPesosAPesos() {
		double precio = this.pieza.getPrecioEn(Moneda.Pesos);
		Assert.assertEquals(precio, 40);
	}
	/**
	 * Obtener el valor de la pieza en pesos con precio en dolares
	 */
	public void obtenerValorDePiezaEnDolaresAPesos() {
		Pieza pieza=new Pieza("",10,Moneda.Dolares);
		Precio.setCotizacionDolaresAPesos(4);
		double precio = this.pieza.getPrecioEn(Moneda.Pesos);
		Assert.assertEquals(precio, 40);
	}
	/**
	 * Obtener el valor de la pieza en dolares con precio en pesos
	 */
	public void obtenerValorDePiezaEnPesosADolares() {
		Precio.setCotizacionDolaresAPesos(4);
		double precio = this.pieza.getPrecioEn(Moneda.Dolares);
		Assert.assertEquals(precio, 10);
	}
	/**
	 * Obtener el valor de la pieza en dolares con precio en dolares
	 */
	public void obtenerValorDePiezaEnDolaresADolares() {
		Pieza pieza=new Pieza("",10,Moneda.Dolares);		
		double precio = this.pieza.getPrecioEn(Moneda.Dolares);
		Assert.assertEquals(precio, 10);
	}

}
