package org.utn.tacs.tp.group2.db.pieza;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Pieza;

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
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.", piezaPersistida1,
				piezaObtenidoConDao);
	}

	/**
	 * Consulta una pieza existente en la BD por su Codigo
	 */
	@Test
	public void consultarUnaPiezaPorCodigo() {
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.", piezaPersistida1
				.getId(), dao.findByCodigo("PIEZA1").getId());
	}

	/**
	 * Consulta las piezas Disponibles de una determinada Categoria.
	 */
	@Test
	public void consultarPiezasDisponiblesPorCategoria() {
		this.piezaPersistida1.setCategoria("PREMIUM");
		this.piezaPersistida2.setCategoria("PREMIUM");
		List<Pieza> piezasPersistidasFromDao = this.dao.findByCategoria("PREMIUM");
		verficarListaResultado(piezasPersistidasFromDao);
	}

	/**
	 * Consulta una pieza segun el auto a la que pertenece.
	 */
	@Test
	public void consultarPiezaPorAuto(){
		this.piezaPersistida1.setAutoOrigen(auto1);
		
		List<Pieza> piezasFromDao = this.dao.findByAuto(auto1);
		Assert.assertEquals(1,piezasFromDao.size());
		Assert.assertTrue(piezasFromDao.contains(piezaPersistida1));
	}

	/**
	 * Consulta un conjunto de piezas segun el auto a la que pertenecen.
	 */
	@Test
	public void consultarPiezasPorAuto(){
		this.piezaPersistida1.setAutoOrigen(auto1);
		this.piezaPersistida2.setAutoOrigen(auto1);
		
		List<Pieza> piezasFromDao = this.dao.findByAuto(auto1);
		Assert.assertEquals(2,piezasFromDao.size());
		Assert.assertTrue(piezasFromDao.contains(piezaPersistida1));
		Assert.assertTrue(piezasFromDao.contains(piezaPersistida2));
	}
	
	/**
	 * Consulta las piezas reservadas.
	 */
	@Test
	public void consultarPiezasReservadas() {
		this.piezaPersistida1.reservar();
		this.piezaPersistida2.reservar();
		List<Pieza> piezasPersistidasFromDao = this.dao.findByEstado("RESERVADA");
		verficarListaResultado(piezasPersistidasFromDao);
	}

	private void verficarListaResultado(List<Pieza> piezasPersistidasFromDao) {
		Assert.assertEquals(2, piezasPersistidasFromDao.size());
		Assert.assertTrue(piezasPersistidasFromDao.contains(piezaPersistida1));
		Assert.assertTrue(piezasPersistidasFromDao.contains(piezaPersistida2));
	}
}
