package org.utn.tacs.tp.group2.service.pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.ext.spring.SpringRouter;
import org.restlet.resource.StringRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;
import org.utn.tacs.tp.group2.service.resources.PedidoResource;

import com.thoughtworks.xstream.XStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testingApplicationContext.xml"})
@Transactional
public class TestIntegracionServicioPedidos {

	@Autowired
	private SpringRouter router;

	@Autowired
	private PedidoResource pr;
	
	private PiezaDAO piezaDao;
	private PedidoDAO pedidoDao;

	private Pedido unPedidoModeloSinPieza;
	private PedidoDTO unPedidoSinPiezaDTO;

	private Pedido unPedidoModeloConPieza;
	
	private Pedido unPedidoEfectivizado;
	private PedidoDTO unPedidoEfectivizadoDTO;
	
	@Before
	public void setUp() {
		this.unPedidoModeloSinPieza = Pedido.create();
		
		this.unPedidoModeloConPieza = Pedido.create();
		this.unPedidoModeloConPieza.addPieza(new Pieza("",12,Moneda.PESO));
		
		this.unPedidoEfectivizado = Pedido.create();
		this.unPedidoEfectivizado.addPieza(new Pieza("",22,Moneda.DOLAR));
		this.unPedidoEfectivizado.efectivizar();
		
		this.pedidoDao = pr.getPedidoService().getPedidoDAO();
		this.piezaDao = pr.getPedidoService().getPiezaDAO();
		this.pedidoDao.save(this.unPedidoModeloSinPieza);
		this.pedidoDao.save(this.unPedidoModeloConPieza);
		this.pedidoDao.save(unPedidoEfectivizado);
		
		this.unPedidoSinPiezaDTO = new PedidoDTO(this.unPedidoModeloSinPieza);
		this.unPedidoEfectivizadoDTO = new PedidoDTO(this.unPedidoEfectivizado);
		
	}
	
	@Test
	public void codigoRespuestaCreandoPedidoValido() {
		Pieza nuevaPieza = new Pieza("CODE",12,Moneda.DOLAR);
		this.piezaDao.save(nuevaPieza);
		List<String> piezaId = new ArrayList<String>();
		piezaId.add(nuevaPieza.getId().toString());
		
		PedidoDTO pedidoDTO = new PedidoDTO(Pedido.create());
		pedidoDTO.setPiezas(piezaId);
		
		Response response = this.router.put("/pedido", new StringRepresentation(new XStream().toXML(pedidoDTO)));
		
		Assert.assertEquals(Status.SUCCESS_CREATED, response.getStatus());
	}

	@Test
	public void codigoRespuestaCreandoPedidoConPiezasNoValidas() {
		Pieza nuevaPieza = new Pieza("CODE",12,Moneda.DOLAR);
		List<String> piezaId = new ArrayList<String>();
		piezaId.add(nuevaPieza.getId().toString());
		
		PedidoDTO pedidoDTO = new PedidoDTO(Pedido.create());
		pedidoDTO.setPiezas(piezaId);
		
		Response response = this.router.put("/pedido", new StringRepresentation(new XStream().toXML(pedidoDTO)));
		
		Assert.assertEquals(Status.CLIENT_ERROR_BAD_REQUEST, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaConsultandoUnPedidoPorId(){
		Response response = router.get("/pedido/" + this.unPedidoModeloSinPieza.getId());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
	
	@Test()
	public void codigoRespuestaConsultandoUnPedidoInexistentePorId() {
		Response response = router.get("/pedido/" + Pedido.create().getId());
		Assert.assertEquals(Status.CLIENT_ERROR_NOT_FOUND, response.getStatus());
	}

	@Test
	public void codigoRespuestaConsultandoPedidosPorEstado() {
		Response response = router.get("/pedido?estado=" + EstadoPedido.getCancelado());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}

	@Test
	public void codigoRespuestaConsultandoPedidosPorEstadoInvalido() {
		Response response = router.get("/pedido?estado=" + "Estado_Fake");
		Assert.assertEquals(Status.CLIENT_ERROR_BAD_REQUEST, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaAgregandoPieza() throws IOException {
		Pieza unaPiezaParaAgregar = new Pieza("",12, Moneda.DOLAR);
		unaPiezaParaAgregar.setAutoOrigen(Auto.createAuto("", "", 2010, new Date()));
		piezaDao.save(unaPiezaParaAgregar);
		
		Response response = router.post("/pedido/" + this.unPedidoModeloSinPieza.getId().toString(),new StringRepresentation(new XStream().toXML(new PiezaDTO(unaPiezaParaAgregar))));
		
		Assert.assertEquals(Status.SUCCESS_NO_CONTENT, response.getStatus());
	}

	
	@Test
	public void codigoRespuestaEfectivizandoPedido() throws IOException {
		Response response = router.post("/pedido/" + this.unPedidoModeloConPieza.getId().toString() + "/accion/efectivizar", new StringRepresentation(""));
		Assert.assertEquals(Status.SUCCESS_NO_CONTENT, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaEfectivizandoPedidoConDatosNoValidos() throws IOException {
		Response response = router.post("/pedido/" + this.unPedidoModeloConPieza.getId().toString() + "/accion/efectivvvvizar", new StringRepresentation(""));
		Assert.assertEquals(Status.CLIENT_ERROR_BAD_REQUEST, response.getStatus());
	}
	
	@Test
	public void codigoRespuestaCancelandoPedido() throws IOException {
		Response response = router.post("/pedido/" + this.unPedidoModeloConPieza.getId().toString() + "/accion/cancelar", new StringRepresentation(""));
		Assert.assertEquals(Status.SUCCESS_NO_CONTENT, response.getStatus());
	}
	
	//********************************************
	//** VALIDACION DE OBJETOS RESPUESTA
	//********************************************
	
	@Test
	public void respuestaConsultandoPorId() throws IOException {
		Response response = router.get("/pedido/" + this.unPedidoModeloSinPieza.getId().toString());
		PedidoDTO pedidoGivenByService = (PedidoDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(this.unPedidoSinPiezaDTO, pedidoGivenByService);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarPedidosPorEstado() throws  IOException {
		Response response = router.get("/pedido?estado=" + EstadoPedido.getEfectivo());		
		
		List<PedidoDTO> pedidosGivenByService = (List<PedidoDTO>) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(1, pedidosGivenByService.size());
		Assert.assertTrue(pedidosGivenByService.contains(this.unPedidoEfectivizadoDTO));
	}
	
	@Test
	public void agregarPieza() throws IOException {
		Pieza unaPiezaParaAgregar = new Pieza("",12, Moneda.DOLAR);
		unaPiezaParaAgregar.setAutoOrigen(Auto.createAuto("", "", 2010, new Date()));
		piezaDao.save(unaPiezaParaAgregar);
		
		Response response = router.post("/pedido/" + this.unPedidoModeloSinPieza.getId().toString(),new StringRepresentation(new XStream().toXML(new PiezaDTO(unaPiezaParaAgregar))));
		response = router.get("/pedido/" + this.unPedidoModeloSinPieza.getId().toString());
		
		PedidoDTO pedidoGivenByService = (PedidoDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(1,pedidoGivenByService.getPiezas().size());
		Assert.assertTrue(pedidoGivenByService.getPiezas().contains(unaPiezaParaAgregar.getId().toString()));
	}

	@Test
	public void efectivizarPedido() throws IOException {
		router.post("/pedido/" + this.unPedidoModeloConPieza.getId().toString() + "/accion/efectivizar", new StringRepresentation(""));
		
		Pedido pedido = pedidoDao.findByID(this.unPedidoModeloConPieza.getId());
		Assert.assertTrue(pedido.isEfectivo());
	}
	
	@Test
	public void cancelarPedido() throws IOException {
		router.post("/pedido/" + this.unPedidoModeloConPieza.getId().toString() + "/accion/cancelar", new StringRepresentation(""));
		
		Pedido pedido = pedidoDao.findByID(this.unPedidoModeloConPieza.getId());
		Assert.assertTrue(pedido.isCancelado());
	}
	
	//********************************************
	//** GETTER & SETTERS
	//********************************************
	
	public SpringRouter getRouter() {
		return router;
	}
	
	public void setRouter(SpringRouter router) {
		this.router = router;
	}
	
	public PiezaDAO getPiezaDao() {
		return piezaDao;
	}
	
	public void setPiezaDao(PiezaDAO piezaDao) {
		this.piezaDao = piezaDao;
	}
	
	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}
	
	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}
	
	
}
