package org.utn.tacs.tp.group2.service.pieza;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.PiezaService;

public class PiezaServiceTest {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	private PiezaService piezaService = (PiezaService) applicationContext.getBean("piezaService");

	private PiezaDAO piezaDAO = (PiezaDAO) applicationContext.getBean("piezaDAO");

	private Pieza pieza;

	@Before
	public void setUp() throws Exception {
		piezaService.getPiezaDAO().beginTransaction();
		pieza = new Pieza();
		piezaDAO.save(pieza);
	}

	@Test
	public void crearPieza() {
		Pieza p = piezaService.newPieza();
		Assert.assertNotNull(p);
	}

	@Test
	public void borrarPieza() {
		piezaService.deletePieza(pieza);
		Assert.assertFalse(piezaDAO.isPersisted(pieza));
	}

	@Test
	public void consultarPiezaById() {
		Pieza p = piezaService.newPieza();
		Pieza piezaLoaded = piezaService.loadPiezaById(p.getId());
		Assert.assertEquals(p, piezaLoaded);
	}

	@After
	public void tearDown() throws Exception {
		// List<Pieza> piezas = piezaDAO.loadAll();
		piezaService.getPiezaDAO().rollbackTransaction();
		// piezaDAO.deleteAll();
	}

}
