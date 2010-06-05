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

	private Pieza unaPiezaPremiumDeAutoA;
	private Pieza unaPiezaMediumDeAutoA;
	private Pieza unaPiezaMediumDeAutoB;

	private Auto autoA;
	private Auto autoB;

	@Before
	public void setUp() throws Exception {
		PiezaDAOMock piezaDAO = new PiezaDAOMock();
		((PiezaServiceImpl)this.piezaService).setPiezaDAO(piezaDAO);
		
		autoA = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
		autoB = Auto.createAuto("EXP-077", "AKA-47", 2002, new Date());
		
		unaPiezaPremiumDeAutoA = new Pieza("PIEZA 1",new BigDecimal(40),Moneda.Pesos);
		unaPiezaPremiumDeAutoA.setCategoria("PREMIUM");
		unaPiezaPremiumDeAutoA.setAutoOrigen(autoA);
		
		unaPiezaMediumDeAutoA = new Pieza("PIEZA 2",new BigDecimal(40),Moneda.Pesos);
		unaPiezaMediumDeAutoA.setCategoria("MEDIUM");
		unaPiezaMediumDeAutoA.setAutoOrigen(autoA);
		
		unaPiezaMediumDeAutoB = new Pieza("PIEZA 3",new BigDecimal(40),Moneda.Pesos);
		unaPiezaMediumDeAutoB.setCategoria("MEDIUM");
		unaPiezaMediumDeAutoB.setAutoOrigen(autoB);
		
		piezaDAO.save(unaPiezaPremiumDeAutoA);
		piezaDAO.save(unaPiezaMediumDeAutoA);
		piezaDAO.save(unaPiezaMediumDeAutoB);
	}

	@Test
	public void consultarPiezaById() {
		Pieza serviceGivenPieza = piezaService.getPiezaById(this.unaPiezaPremiumDeAutoA.getId());
		Assert.assertEquals(this.unaPiezaPremiumDeAutoA, serviceGivenPieza);
	}

	@Test
	public void consultarPiezasByCategoriaPremium() {
		List<Pieza> piezas = piezaService.loadPiezasByCategoria("PREMIUM");
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getCategoria(), unaPiezaPremiumDeAutoA.getCategoria());
		}
	}

	@Test
	public void consultarPiezasByCategoriaMedium() {
		List<Pieza> piezas = piezaService.loadPiezasByCategoria("MEDIUM");
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getCategoria(), unaPiezaPremiumDeAutoA.getCategoria());
		}
	}
	
	@Test
	public void consulterPiezasByAuto() {
		List<Pieza> piezas = piezaService.loadPiezasByAuto(autoA);
		Assert.assertFalse(piezas.isEmpty());
		for (Pieza p : piezas) {
			Assert.assertEquals(p.getAutoOrigen(), unaPiezaPremiumDeAutoA.getAutoOrigen());
		}
	}
	
	@Test
	public void consultarPorcentajePiezasVendidasDeUnAuto() {
		// TODO: hacer...
	}
	
	
	@After
	public void tearDown() throws Exception {
	}

}
