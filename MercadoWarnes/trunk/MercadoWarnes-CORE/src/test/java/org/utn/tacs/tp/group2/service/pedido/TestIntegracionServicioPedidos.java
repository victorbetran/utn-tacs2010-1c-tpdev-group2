package org.utn.tacs.tp.group2.service.pedido;

import java.io.IOException;

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
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;

import com.thoughtworks.xstream.XStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestIntegracionServicioPedidos {

	@Autowired
	private SpringRouter router;
	
	@Autowired
	private PiezaDAO piezaDao;

	@Autowired
	private PedidoDAO pedidoDao;

	private Pedido unPedidoModelo;
	private PedidoDTO unPedidoDTO;
	
	private Pedido unPedidoEfectivizado;
	private PedidoDTO unPedidoEfectivizadoDTO;
	
	@Before
	public void setUp() {
		this.unPedidoModelo = Pedido.createPedido();
		
		this.unPedidoEfectivizado = Pedido.createPedido();
		this.unPedidoEfectivizado.addPieza(new Pieza("",22,Moneda.Dolares));
		this.unPedidoEfectivizado.efectivizar();
		
		
		this.pedidoDao.save(this.unPedidoModelo);
		this.pedidoDao.save(unPedidoEfectivizado);
		
		this.unPedidoDTO = new PedidoDTO(this.unPedidoModelo);
		this.unPedidoEfectivizadoDTO = new PedidoDTO(this.unPedidoEfectivizado);
	}
	
	@Transactional
	@Test
	public void codigoRespuestaConsultandoUnPedidoPorId(){
		Response response = router.get("/pedido-byId/" + this.unPedidoModelo.getId());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
	
	@Transactional
	@Test
	public void codigoRespuestaConsultandoUnPedidoInexistentePorId() {
		Response response = router.get("/pedido-byId/" + Pedido.createPedido());
		Assert.assertEquals(Status.CLIENT_ERROR_NOT_FOUND, response.getStatus());
	}
	
	@Transactional
	@Test
	public void respuestaConsultandoPorId() throws IOException {
		Response response = router.get("/pedido-byId/" + this.unPedidoModelo.getId().toString());
		PedidoDTO pedidoGivenByService = (PedidoDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(this.unPedidoDTO, pedidoGivenByService);
	}
	
	@Transactional
	@Test
	public void crearPedido() throws IOException {
		Response response = router.get("/pedido-create");
		PedidoDTO pedidoGivenByService = (PedidoDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertTrue(this.pedidoDao.existsId(Long.valueOf(pedidoGivenByService.getId())));
	}
	
//	@SuppressWarnings("unchecked")
//	@Transactional
//	@Test
//	public void consultarPedidosPorEstado() throws IOException {
//		Response response = router.get("/pedido-byState/" + EstadoPedido.getEfectivo().toString());		
//		
//		List<PedidoDTO> pedidosGivenByService = (List<PedidoDTO>) new XStream().fromXML(response.getEntity().getStream());
//		
//		Assert.assertEquals(1, pedidosGivenByService.size());
//		Assert.assertTrue(pedidosGivenByService.contains(this.unPedidoEfectivizadoDTO));
//	}
	
	@Transactional
	@Test
	public void agregarPieza() throws IOException {
		Pieza unaPiezaParaAgregar = new Pieza("",12, Moneda.Dolares);
		piezaDao.save(unaPiezaParaAgregar);
		
		Response response = router.get("/pedido-byId/" + this.unPedidoModelo.getId().toString() + "/addPieza/" + unaPiezaParaAgregar.getId().toString());
		response = router.get("/pedido-byId/" + this.unPedidoModelo.getId().toString());
		
		PedidoDTO pedidoGivenByService = (PedidoDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(1,pedidoGivenByService.getPiezas().size());
		Assert.assertTrue(pedidoGivenByService.getPiezas().contains(unaPiezaParaAgregar.getId().toString()));
	}

//	@Transactional
//	@Test
//	public void efectivizarPedido() throws IOException {
//		router.get("/pedido-byId/" + this.unPedidoModelo.getId().toString() + "/efectivizar");
//		
//		Pedido pedido = pedidoDao.findByID(this.unPedidoModelo.getId());
//		Assert.assertTrue(pedido.isEfectivo());
//	}
	
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
