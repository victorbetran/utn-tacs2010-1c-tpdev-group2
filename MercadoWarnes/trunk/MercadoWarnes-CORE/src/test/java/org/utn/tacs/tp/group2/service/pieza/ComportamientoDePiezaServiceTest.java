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
import org.utn.tacs.tp.group2.service.implementation.PiezaServiceImpl;

public class ComportamientoDePiezaServiceTest {

	@Autowired
	private PiezaServiceImpl piezaService = new PiezaServiceImpl();

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
		
		unaPiezaPremiumDeAutoA = new Pieza("PIEZA 1",50,Moneda.Dolares);
		unaPiezaPremiumDeAutoA.setCategoria("PREMIUM");
		unaPiezaPremiumDeAutoA.setAutoOrigen(autoA);
		
		unaPiezaMediumDeAutoA = new Pieza("PIEZA 2",22,Moneda.Pesos);
		unaPiezaMediumDeAutoA.setCategoria("MEDIUM");
		unaPiezaMediumDeAutoA.setAutoOrigen(autoA);
		
		unaPiezaMediumDeAutoB = new Pieza("PIEZA 3",45,Moneda.Pesos);
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

	@Test()
	public void consultarPiezaInexistente() {
		Assert.assertNull(piezaService.getPiezaById(new Pieza("PIEZA INEXISTENTE",40,Moneda.Pesos).getId()));		
	}
	
	@Test
	public void consultarPiezasByCategoriaPremium() {
		List<Pieza> piezasGivenByService = piezaService.getPiezasByCategoria("PREMIUM");
		Assert.assertEquals(1, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaPremiumDeAutoA));
	}

	@Test
	public void consultarPiezasByCategoriaMedium() {
		List<Pieza> piezasGivenByService = piezaService.getPiezasByCategoria("MEDIUM");
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoA));
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoB));
	}
	
	@Test
	public void consultarPiezasByCategoriaSinResultado() {
		List<Pieza> piezasGivenByService = piezaService.getPiezasByCategoria("NO-EXISTING-CATEGORY");
		Assert.assertTrue(piezasGivenByService.isEmpty());
	}
	
	@Test
	public void consultarPiezasByAuto() {
		List<Pieza> piezasGivenByService = piezaService.getPiezasByAuto(this.autoA.getId().toString());
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaPremiumDeAutoA));
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoA));
	}
	
	@Test
	public void consulterPiezasByAutoSinResultado() {
		List<Pieza> piezasGivenByService = piezaService.getPiezasByAuto(Auto.createAuto("", "", 2010, new Date()).getId().toString());
		Assert.assertTrue(piezasGivenByService.isEmpty());
	}
	
	@Test
	public void consultarPorcentajePiezasVendidasDeUnAuto() {
		this.unaPiezaPremiumDeAutoA.reservar();
		this.unaPiezaPremiumDeAutoA.vender();
		int porcentaje = piezaService.getPorcentajePiezasVendidasByAuto(this.autoA.getId().toString());
		Assert.assertEquals(porcentaje, 50);
	}

	@Test
	public void consultarPiezasPorEstadoReservado() {
		this.unaPiezaMediumDeAutoA.reservar();
		
		List<Pieza> piezasGivenByService = piezaService.getPiezasReservadas();
		Assert.assertEquals(1,piezasGivenByService.size());
		
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoA));
	}
	
}
