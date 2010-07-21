package org.utn.tacs.tp.group2.service.pieza;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;
import org.utn.tacs.tp.group2.service.implementation.PiezaServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testingApplicationContext.xml"})
public class ComportamientoDePiezaServiceTest {

	private PiezaService piezaService = new PiezaServiceImpl();

	private Pieza unaPiezaPremiumDeAutoA;
	private Pieza unaPiezaMediumDeAutoA;
	private Pieza unaPiezaMediumDeAutoB;
	
	private PiezaDTO unaPiezaPremiumDeAutoADTO;
	private PiezaDTO unaPiezaMediumDeAutoADTO;
	private PiezaDTO unaPiezaMediumDeAutoBDTO;

	private Auto autoA;
	private Auto autoB;

	@Before
	public void setUp() throws Exception {
		
		PiezaDAOMock piezaDAO = new PiezaDAOMock();
		this.piezaService.setPiezaDAO(piezaDAO);
		
		autoA = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
		autoB = Auto.createAuto("EXP-077", "AKA-47", 2002, new Date());
		
		unaPiezaPremiumDeAutoA = new Pieza("PIEZA 1 - gato",50,Moneda.DOLAR);
		unaPiezaPremiumDeAutoA.setCategoria("PREMIUM");
		unaPiezaPremiumDeAutoA.setAutoOrigen(autoA);
		
		unaPiezaMediumDeAutoA = new Pieza("PIEZA 2",22,Moneda.PESO);
		unaPiezaMediumDeAutoA.setCategoria("MEDIUM");
		unaPiezaMediumDeAutoA.setAutoOrigen(autoA);
		
		unaPiezaMediumDeAutoB = new Pieza("PIEZA 3",45,Moneda.PESO);
		unaPiezaMediumDeAutoB.setCategoria("MEDIUM");
		unaPiezaMediumDeAutoB.setAutoOrigen(autoB);
		
		piezaDAO.save(unaPiezaPremiumDeAutoA);
		piezaDAO.save(unaPiezaMediumDeAutoA);
		piezaDAO.save(unaPiezaMediumDeAutoB);
		
		this.unaPiezaPremiumDeAutoADTO = new PiezaDTO(unaPiezaPremiumDeAutoA);
		this.unaPiezaMediumDeAutoADTO = new PiezaDTO(unaPiezaMediumDeAutoA);
		this.unaPiezaMediumDeAutoBDTO = new PiezaDTO(unaPiezaMediumDeAutoB);
	}
	
	@Test
	public void consultarPiezaById() {
		PiezaDTO piezaGivenByService = piezaService.getPiezaById(this.unaPiezaPremiumDeAutoA.getId().toString());
		Assert.assertEquals(this.unaPiezaPremiumDeAutoADTO, piezaGivenByService);
	}

	@Test()
	public void consultarPiezaInexistente() {
		Assert.assertNull(piezaService.getPiezaById(new Pieza("PIEZA INEXISTENTE",40,Moneda.PESO).getId().toString()));		
	}
	
	@Test
	public void consultarPiezasByCategoriaPremium() {
		List<PiezaDTO> piezasGivenByService = piezaService.getPiezasByCategoria("PREMIUM");
		Assert.assertEquals(1, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaPremiumDeAutoADTO));
	}

	@Test
	public void consultarPiezasByCategoriaMedium() {
		List<PiezaDTO> piezasGivenByService = piezaService.getPiezasByCategoria("MEDIUM");
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoADTO));
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoBDTO));
	}
	
	@Test
	public void consultarPiezasByCategoriaSinResultado() {
		List<PiezaDTO> piezasGivenByService = piezaService.getPiezasByCategoria("NO-EXISTING-CATEGORY");
		Assert.assertTrue(piezasGivenByService.isEmpty());
	}
	
	@Test
	public void consultarPiezasByAuto() {
		List<PiezaDTO> piezasGivenByService = piezaService.getPiezasByAuto(this.autoA.getId().toString());
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaPremiumDeAutoADTO));
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaMediumDeAutoADTO));
	}
	
	@Test
	public void consulterPiezasByAutoSinResultado() {
		List<PiezaDTO> piezasGivenByService = piezaService.getPiezasByAuto(Auto.createAuto("", "", 2010, new Date()).getId().toString());
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
		PiezaDTO unaPiezaMediumDeAutoADTO_Reservada = new PiezaDTO(this.unaPiezaMediumDeAutoA);
		
		List<PiezaDTO> piezasGivenByService = piezaService.getPiezasByEstado(EstadoPieza.getEstadoReservada().toString());
		Assert.assertEquals(1,piezasGivenByService.size());
		
		Assert.assertTrue(piezasGivenByService.contains(unaPiezaMediumDeAutoADTO_Reservada));
	}
	
}
