package org.utn.tacs.tp.group2.service.pieza;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;
import org.utn.tacs.tp.group2.service.implementation.PiezaServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PiezaServiceTest {

	@Autowired
	private PiezaService piezaService;

	private Pieza pieza1;
	private Pieza pieza2;
	private Pieza pieza3;

	private Auto auto;

	@Before
	public void setUp() throws Exception {
		((PiezaServiceImpl)this.piezaService).setPiezaDAO(new PiezaDAOMock());
		
		pieza1 = new Pieza("PIEZA 1",new BigDecimal(40),Moneda.Pesos);
		pieza1.setCategoria("PREMIUM");
		
		auto = new Auto();
		auto.setModelo("AK-47");
		auto.setAnio(2009);
		auto.setPatente("EXP-074");
		
		pieza1.setAutoOrigen(auto);
		
		piezaService.getPiezaDAO().save(pieza1);
		
		pieza2 = new Pieza("PIEZA 2",new BigDecimal(40),Moneda.Pesos);
		pieza3 = new Pieza("PIEZA 3",new BigDecimal(40),Moneda.Pesos);
		
		piezaService.getPiezaDAO().save(pieza2);
		piezaService.getPiezaDAO().save(pieza3);
	}

	@Transactional
	@Test
	public void crearPieza() {
		Pieza p = piezaService.newPieza();
		Assert.assertNotNull(p);
	}

	@Transactional
	@Test
	public void borrarPieza() {
		piezaService.delete(pieza1);
		Assert.assertFalse(piezaService.getPiezaDAO().isPersisted(pieza1));
	}

	// TODO: Ver como lograr hacer andar este test.
	// @Transactional
	// @Test
	public void consultarPiezaById() {
		Pieza p = piezaService.newPieza();
		Pieza piezaLoaded = piezaService.loadPiezaById(p.getId());
		Assert.assertEquals(p, piezaLoaded);
	}

	@Transactional
	@Test
	public void consultarPiezasByCategoria() {
		List<Pieza> piezas = piezaService.loadPiezasByCategoria("PREMIUM");
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getCategoria(), pieza1.getCategoria());
		}
	}

	@Transactional
	@Test
	public void consulterPiezasByAuto() {
		List<Pieza> piezas = piezaService.loadPiezasByAuto(auto);
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getAutoOrigen(), pieza1.getAutoOrigen());
		}
	}
	
	@Transactional
	@Test
	public void consultarPorcentajePiezasVendidasDeUnAuto() {
		// TODO: hacer...
	}
	
	
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
//		piezaService.getPiezaDAO().rollbackTransaction();
		// piezaDAO.deleteAll();
	}

}
