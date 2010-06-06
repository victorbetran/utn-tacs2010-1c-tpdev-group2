package org.utn.tacs.tp.group2.service.pieza;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.ext.spring.SpringRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestIntegracionServicioPiezas {

	@Autowired
	private SpringRouter router;
	
	@Autowired
	private PiezaDAO piezaDao;

	private Pieza unaPieza;
	private Pieza otraPieza;
	
	private Auto auto1;
	private Auto auto2;
	
	@Before
	public void setUp() {
		auto1 = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
		auto2 = Auto.createAuto("FAST", "BMW-001", 2001, new Date());
		
		this.unaPieza = new Pieza("PIEZA1",new BigDecimal(40),Moneda.Pesos).setAutoOrigen(auto1);
		this.otraPieza = new Pieza("PIEZA2",new BigDecimal(40),Moneda.Pesos).setAutoOrigen(auto2);
		
		this.piezaDao.save(this.unaPieza);
		this.piezaDao.save(this.otraPieza);
	}
	
	@Transactional
	@Test
	public void codigoRespuestaConsultandoUnaPiezaPorId(){
		Response response = router.get("/pieza/" + this.unaPieza.getId());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
	
	@Transactional
	@Test
	public void codigoRespuestaConsultandoUnaPiezaInexistentePorId(){
		Response response = router.get("/pieza/" + new Pieza("",new BigDecimal(20),Moneda.Dolares).getId());
		Assert.assertEquals(Status.CLIENT_ERROR_NOT_FOUND, response.getStatus());
	}
	
	@Transactional
	@Test
	public void codigoRespuestaConsultandoTodasLasPiezas(){
		Response response = router.get("/pieza/all");
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}

}
