package org.utn.tacs.tp.group2.db.pieza;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class ConsultaDePiezasTest extends PiezaTest {

	Pieza piezaPersistida1;

	Pieza piezaPersistida2;

	Auto auto1;

	@Override
	public void setUp() {
		super.setUp();

		this.piezaPersistida1 = new Pieza("PIEZA1");
		this.dao.save(this.piezaPersistida1);

		this.piezaPersistida2 = new Pieza("PIEZA2");
		this.dao.save(this.piezaPersistida2);

		this.auto1 = new Auto();

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
