package org.utn.tacs.tp.group2.persistence.pieza;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.domain.pieza.Auto;
import org.utn.tacs.tp.group2.domain.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.domain.pieza.Pieza;

public class ConsultaDePiezasTest extends PiezaTest {

	private Pieza piezaPersistida1;
	private Pieza piezaPersistida2;
	
	private Auto auto1;
	private Auto auto2;

	@Override
	public void setUp() {
		super.setUp();

		this.piezaPersistida1 = new Pieza("PIEZA1");
		this.dao.save(this.piezaPersistida1);

		this.piezaPersistida2 = new Pieza("PIEZA2");
		this.dao.save(this.piezaPersistida2);

		auto1 = new Auto();
		auto1.setModelo("AK-47");
		auto1.setAnio(2009);
		auto1.setPatente("EXP-074");

		auto2 = new Auto();
		auto2.setModelo("FAST");
		auto2.setAnio(2001);
		auto2.setPatente("BMW-001");
		
	}

	@Test
	public void consultarPiezaPorIDTest() {
		Pieza piezaObtenidoConDao = dao.findByID(piezaPersistida1.getId());
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.", piezaPersistida1,piezaObtenidoConDao);
	}

	/**
	 * Consulta una pieza existente en la BD por su Codigo
	 */
	@Test
	public void consultarUnaPiezaPorCodigo() {
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.", piezaPersistida1.getId(), dao.findByCodigo("PIEZA1").getId());
	}

	/**
	 * Consulta piezas segun una categoria.
	 */
	@Test
	public void consultarPiezasPorCategoria() {
		this.piezaPersistida1.setCategoria("PREMIUM");
		this.piezaPersistida2.setCategoria("PREMIUM");
		
		assertList(this.dao.findByCategoria("PREMIUM"), this.piezaPersistida1,this.piezaPersistida2);
	}

	/**
	 * Consulta una pieza segun el auto a la que pertenece.
	 */
	@Test
	public void consultarPiezaPorAuto(){
		this.piezaPersistida1.setAutoOrigen(auto1);
		this.piezaPersistida2.setAutoOrigen(auto2);
		
		assertList(this.dao.findByAuto(auto1), this.piezaPersistida1);
	}

	/**
	 * Consulta un conjunto de piezas segun el auto a la que pertenecen.
	 */
	@Test
	public void consultarPiezasPorAuto(){
		this.piezaPersistida1.setAutoOrigen(auto1);
		this.piezaPersistida2.setAutoOrigen(auto1);
		
		assertList(this.dao.findByAuto(auto1), this.piezaPersistida1, this.piezaPersistida2);
	}
	
	/**
	 * Consulta las piezas disponibles.
	 */
	@Test
	public void consultarPiezasDisponibles() {
		assertList(this.dao.findByEstado(EstadoPieza.getEstadoDisponible()), this.piezaPersistida1, this.piezaPersistida2);
	}
	
	/**
	 * Consulta las piezas reservadas.
	 */
	@Test
	public void consultarPiezasReservadas() {
		this.piezaPersistida1.reservar();
		this.piezaPersistida2.reservar();
		
		assertList(this.dao.findByEstado(EstadoPieza.getEstadoReservada()), this.piezaPersistida1, this.piezaPersistida2);
	}

	/**
	 * Consulta las piezas vendidas.
	 */
	@Test
	public void consultarPiezasVendidas() {
		this.piezaPersistida1.reservar();
		this.piezaPersistida1.vender();
		assertList(this.dao.findByEstado(EstadoPieza.getEstadoVendida()), this.piezaPersistida1);
	}
	
	// *******************************************************************************
	// *** HELPER
	// *******************************************************************************
	
	private void assertList(List<Pieza> toBeChecked,Pieza ...expected) {
		int qty = expected.length;
		
		Assert.assertEquals("La cantidad de elementos en la lista no es la esperada.",qty, toBeChecked.size());
		for (Pieza piezaExpected : expected) {
			Assert.assertTrue("Uno de los elemenos esperados no se encuentra en el conjunto.",toBeChecked.contains(piezaExpected));
		}
	}

}
