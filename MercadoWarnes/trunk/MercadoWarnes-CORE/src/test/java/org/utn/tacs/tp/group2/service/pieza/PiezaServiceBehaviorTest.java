package org.utn.tacs.tp.group2.service.pieza;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;
import org.utn.tacs.tp.group2.service.implementation.PiezaServiceImpl;

public class PiezaServiceBehaviorTest {

	@Autowired
	private PiezaService piezaService = new PiezaServiceImpl();

	private Pieza unaPiezaPremiumDeAutoA;
	private Pieza unaPiezaMediumDeAutoA;
	private Pieza unaPiezaMediumDeAutoB;

	private Auto autoA;
	private Auto autoB;

	@Before
	public void setUp() throws Exception {
		PiezaDAOMockToTestPiezaServiceBehavior piezaDAO = new PiezaDAOMockToTestPiezaServiceBehavior();
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
		Pieza piezaGivenByService = piezaService.getPiezaById(this.unaPiezaPremiumDeAutoA.getId());
		Assert.assertEquals(this.unaPiezaPremiumDeAutoA, piezaGivenByService);
	}

	@Test
	public void consultarPiezaInexistente() {
		// TODO: hacer.
	}
	
	@Test
	public void consultarPiezasByCategoriaPremium() {
		List<Pieza> piezasGivenByService = piezaService.loadPiezasByCategoria("PREMIUM");
		Assert.assertEquals(1, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaPremiumDeAutoA));
	}

	@Test
	public void consultarPiezasByCategoriaMedium() {
		List<Pieza> piezasGivenByService = piezaService.loadPiezasByCategoria("MEDIUM");
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoA));
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoB));
	}
	
	@Test
	public void consultarPiezasByCategoriaSinResultado() {
		// TODO: hacer.
	}
	
	@Test
	public void consulterPiezasByAuto() {
		List<Pieza> piezasGivenByService = piezaService.loadPiezasByAuto(this.autoA);
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaPremiumDeAutoA));
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoA));
	}
	
	@Test
	public void consulterPiezasByAutoSinResultado() {
		// TODO: hacer.
	}
	
	@Test
	public void consultarPorcentajePiezasVendidasDeUnAuto() {
		// TODO: hacer.
	}

	@Test
	public void consultarPiezasPorEstadoReservado() {
		// TODO: hacer.
	}
	
	@Test
	public void consultarPiezasPorEstadoReservadoSinResultado() {
		// TODO: hacer.
	}
	

}
