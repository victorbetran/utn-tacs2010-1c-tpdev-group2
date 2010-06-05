package org.utn.tacs.tp.group2.service.pieza;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;
import org.utn.tacs.tp.group2.service.implementation.PiezaServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PiezaServiceBehaviorTest {

	@Autowired
	private PiezaService piezaService;

	private Pieza unaPiezaDeAutoA;
	private Pieza otraPiezaDeAutoA;
	private Pieza unaPiezaDeAutoB;

	private Auto autoA;
	private Auto autoB;

	@Before
	public void setUp() throws Exception {
		PiezaDAOMock piezaDAO = new PiezaDAOMock();
		((PiezaServiceImpl)this.piezaService).setPiezaDAO(piezaDAO);
		
		autoA = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
		autoB = Auto.createAuto("EXP-077", "AKA-47", 2002, new Date());
		
		unaPiezaDeAutoA = new Pieza("PIEZA 1",new BigDecimal(40),Moneda.Pesos);
		unaPiezaDeAutoA.setCategoria("PREMIUM");
		unaPiezaDeAutoA.setAutoOrigen(autoA);
		
		otraPiezaDeAutoA = new Pieza("PIEZA 2",new BigDecimal(40),Moneda.Pesos);
		otraPiezaDeAutoA.setCategoria("MEDIUM");
		otraPiezaDeAutoA.setAutoOrigen(autoA);
		
		unaPiezaDeAutoB = new Pieza("PIEZA 3",new BigDecimal(40),Moneda.Pesos);
		unaPiezaDeAutoB.setCategoria("MEDIUM");
		unaPiezaDeAutoB.setAutoOrigen(autoB);
		
		piezaDAO.save(unaPiezaDeAutoA);
		piezaDAO.save(otraPiezaDeAutoA);
		piezaDAO.save(unaPiezaDeAutoB);
	}

	// TODO: Ver como lograr hacer andar este test.
	// @Transactional
	// @Test
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
			Assert.assertEquals(p.getCategoria(), unaPiezaDeAutoA.getCategoria());
		}
	}

	@Test
	public void consulterPiezasByAuto() {
		List<Pieza> piezas = piezaService.loadPiezasByAuto(autoA);
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getAutoOrigen(), unaPiezaDeAutoA.getAutoOrigen());
		}
	}
	
	@Test
	public void consultarPorcentajePiezasVendidasDeUnAuto() {
		// TODO: hacer...
	}
	
	
//	public void consultarPiezasReservadas() {
//		pieza2.reservar();
//		pieza3.reservar();		
//		List<Pieza> piezas = piezaService.loadPiezasReservadas();		
//		Assert.assertFalse(piezas.isEmpty());
//		Assert.assertTrue(piezas.contains(pieza2));
//		Assert.assertTrue(piezas.contains(pieza3));		
//	}

	@After
	public void tearDown() throws Exception {
	}

}
