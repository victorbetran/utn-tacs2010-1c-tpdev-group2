package org.utn.tacs.tp.group2.service.pieza;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.PiezaService;

public class PiezaServiceTest {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	private PiezaService piezaService = (PiezaService) applicationContext.getBean("piezaService");

	private Pieza pieza1;
	private Pieza pieza2;
	private Pieza pieza3;

	private Auto auto;

	@Before
	public void setUp() throws Exception {
		piezaService.getPiezaDAO().beginTransaction();
		pieza1 = new Pieza("PIEZA 1");
		pieza1.setCategoria("PREMIUM");
		
		auto = new Auto();
		auto.setModelo("AK-47");
		auto.setAnio(2009);
		auto.setPatente("EXP-074");
		
		pieza1.setAutoOrigen(auto);
		
		piezaService.getPiezaDAO().save(pieza1);
		
		pieza2 = new Pieza("PIEZA 2");
		pieza3 = new Pieza("PIEZA 3");
		
		piezaService.getPiezaDAO().save(pieza2);
		piezaService.getPiezaDAO().save(pieza3);
	}

	@Test
	public void crearPieza() {
		Pieza p = piezaService.newPieza();
		Assert.assertNotNull(p);
	}

	@Test
	public void borrarPieza() {
		piezaService.deletePieza(pieza1);
		Assert.assertFalse(piezaService.getPiezaDAO().isPersisted(pieza1));
	}

	@Test
	public void consultarPiezaById() {
		Pieza p = piezaService.newPieza();
		Pieza piezaLoaded = piezaService.loadPiezaById(p.getId());
		Assert.assertEquals(p, piezaLoaded);
	}

	@Test
	public void consultarPiezasByCategoria() {
		List<Pieza> piezas = piezaService.loadPiezasByCategoria("PREMIUM");
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getCategoria(), pieza1.getCategoria());
		}
	}

	@Test
	public void consulterPiezasByAuto() {
		List<Pieza> piezas = piezaService.loadPiezasByAuto(auto);
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getAutoOrigen(), pieza1.getAutoOrigen());
		}
	}
	
	@Test
	public void consultarPorcentajePiezasVendidasDeUnAuto() {
		// TODO: hacer...
	}
	
	@Test
	public void consultarPiezasReservadas() {
		pieza2.reservar();
		pieza3.reservar();		
		List<Pieza> piezas = piezaService.loadPiezasReservadas();		
		Assert.assertFalse(piezas.isEmpty());
		Assert.assertTrue(piezas.contains(pieza2));
		Assert.assertTrue(piezas.contains(pieza3));		
	}

	@After
	public void tearDown() throws Exception {
		// List<Pieza> piezas = piezaDAO.loadAll();
		piezaService.getPiezaDAO().rollbackTransaction();
		// piezaDAO.deleteAll();
	}

}
