package org.utn.tacs.tp.group2.service.pieza;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

import com.thoughtworks.xstream.XStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class TestIntegracionServicioPiezas {

	@Autowired
	private SpringRouter router;
	
	@Autowired
	private PiezaDAO piezaDao;

	private Pieza unaPiezaModelo;
	private Pieza otraPiezaModelo;
	
	private Auto autoConPiezas;
	private Auto autoSinPiezas;
	
	private PiezaDTO unaPiezaDTO;
	private PiezaDTO otraPiezaDTO;
	
	@Before
	public void setUp() {
		autoConPiezas = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
		autoSinPiezas = Auto.createAuto("FAST", "BMW-001", 2001, new Date());
		
		this.unaPiezaModelo = new Pieza("PIEZA1",40,Moneda.Pesos).setAutoOrigen(autoConPiezas);
		this.unaPiezaModelo.setCategoria("MEDIUM");
		
		this.otraPiezaModelo = new Pieza("PIEZA2",40,Moneda.Pesos).setAutoOrigen(autoConPiezas);
		this.otraPiezaModelo.setCategoria("MEDIUM");
		
		this.piezaDao.save(this.unaPiezaModelo);
		this.piezaDao.save(this.otraPiezaModelo);
		
		this.unaPiezaDTO = new PiezaDTO(this.unaPiezaModelo);
		this.otraPiezaDTO = new PiezaDTO(this.otraPiezaModelo);

	}
	
	@Test
	public void codigoRespuestaConsultandoUnaPiezaPorId(){
		Response response = router.get("/pieza/" + this.unaPiezaModelo.getId());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaConsultandoUnaPiezaInexistentePorId(){
		Response response = router.get("/pieza/" + new Pieza("",20,Moneda.Dolares).getId());
		Assert.assertEquals(Status.CLIENT_ERROR_NOT_FOUND, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaConsultandoUnaPiezaConIdInvalido(){
		Response response = router.get("/pieza/" + "343rs2ds2");
		Assert.assertEquals(Status.CLIENT_ERROR_BAD_REQUEST, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaConsultandoTodasLasPiezas(){
		Response response = router.get("/pieza");
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}

	@Test
	public void codigoRespuestaConsultandoPorEstadoInvalido(){
		Response response = router.get("/pieza?estado=noexiste");
		Assert.assertEquals(Status.CLIENT_ERROR_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void codigoRespuestaConsultandoPorAuto(){
		Response response = router.get("/pieza?id-auto=" + this.autoConPiezas.getId());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaConsultandoPorAutoInvalido(){
		Response response = router.get("/pieza?id-auto=noexiste");
		Assert.assertEquals(Status.CLIENT_ERROR_BAD_REQUEST, response.getStatus());
	}
	
	
	//********************************************
	//** VALIDACION DE OBJETOS RESPUESTA
	//********************************************
	
	@SuppressWarnings("unchecked")
	@Test
	public void respuestaConsultandoTodasLasPiezas() throws IOException{
		Response response = router.get("/pieza");
		List<PiezaDTO> piezasGivenByService = (List<PiezaDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaDTO));
		Assert.assertTrue(piezasGivenByService.contains(this.otraPiezaDTO));
	}
	
	@Test
	public void respuestaConsultandoPiezaPorId() throws IOException{
		Response response = router.get("/pieza/" + this.unaPiezaDTO.getId());
		PiezaDTO piezaGivenByService = (PiezaDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(this.unaPiezaDTO,piezaGivenByService);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void respuestaConsultandoPiezasPorCategoria() throws IOException{
		Response response = router.get("/pieza/?categoria=MEDIUM");
		List<PiezaDTO> piezasGivenByService = (List<PiezaDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaDTO));
		Assert.assertTrue(piezasGivenByService.contains(this.otraPiezaDTO));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void respuestaConsultandoPiezasPorCategoriaSinResultados() throws IOException{
		Response response = router.get("/pieza/?categoria=PREMIUM");
		List<PiezaDTO> piezasGivenByService = (List<PiezaDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertTrue(piezasGivenByService.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void respuestaConsultandoPiezasPorAuto() throws IOException{
		Response response = router.get("/pieza/?id-auto=" + this.autoConPiezas.getId());
		List<PiezaDTO> piezasGivenByService = (List<PiezaDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(2, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaDTO));
		Assert.assertTrue(piezasGivenByService.contains(this.otraPiezaDTO));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void respuestaConsultandoPiezasPorAutoSinResultados() throws IOException{
		Response response = router.get("/pieza/?id-auto=" + this.autoSinPiezas.getId());
		List<PiezaDTO> piezasGivenByService = (List<PiezaDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertTrue(piezasGivenByService.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void respuestaConsultandoPiezasReservadas() throws IOException{
		this.unaPiezaModelo.reservar();
		this.unaPiezaDTO = new PiezaDTO(this.unaPiezaModelo);
		
		Response response = router.get("/pieza/?estado=" + EstadoPieza.getEstadoReservada().getDescripcion());
		List<PiezaDTO> piezasGivenByService = (List<PiezaDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(1, piezasGivenByService.size());
		Assert.assertTrue(piezasGivenByService.contains(this.unaPiezaDTO));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void respuestaConsultandoPiezasReservadasSinResultados() throws IOException{
		Response response = router.get("/pieza?estado=" + EstadoPieza.getEstadoReservada().getDescripcion());
		List<PiezaDTO> piezasGivenByService = (List<PiezaDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertTrue(piezasGivenByService.isEmpty());
	}
	
}
