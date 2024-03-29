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
import org.utn.tacs.tp.group2.daos.exceptions.PedidoInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;

import com.thoughtworks.xstream.XStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class TestIntegracionServicioPedidos {

	@Autowired
	private SpringRouter router;
	
	@Autowired
	private PiezaDAO piezaDao;

	@Autowired
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
		this.unPedidoModeloConPieza.addPieza(new Pieza("",12,Moneda.Pesos));
		
		this.unPedidoEfectivizado = Pedido.create();
		this.unPedidoEfectivizado.addPieza(new Pieza("",22,Moneda.Dolares));
		this.unPedidoEfectivizado.efectivizar();
		
		this.pedidoDao.save(this.unPedidoModeloSinPieza);
		this.pedidoDao.save(this.unPedidoModeloConPieza);
		this.pedidoDao.save(unPedidoEfectivizado);
		
		this.unPedidoSinPiezaDTO = new PedidoDTO(this.unPedidoModeloSinPieza);
		this.unPedidoEfectivizadoDTO = new PedidoDTO(this.unPedidoEfectivizado);
		
	}
	

	@Test
	public void codigoRespuestaConsultandoUnPedidoPorId(){
		Response response = router.get("/pedido-byId/" + this.unPedidoModeloSinPieza.getId());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
	
	@Test(expected=PedidoInexistenteException.class)
	public void codigoRespuestaConsultandoUnPedidoInexistentePorId() {
		router.get("/pedido-byId/" + Pedido.create());
	}
	
	@Test
	public void respuestaConsultandoPorId() throws IOException {
		Response response = router.get("/pedido-byId/" + this.unPedidoModeloSinPieza.getId().toString());
		PedidoDTO pedidoGivenByService = (PedidoDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertEquals(this.unPedidoSinPiezaDTO, pedidoGivenByService);
	}
	
	@Test
	public void crearPedido() throws IOException {
		Response response = router.get("/pedido-create");
		PedidoDTO pedidoGivenByService = (PedidoDTO) new XStream().fromXML(response.getEntity().getStream());
		
		Assert.assertTrue(this.pedidoDao.existsId(Long.valueOf(pedidoGivenByService.getId())));
	}
	
	@Test
	public void efectivizarPedido() throws IOException {
		router.get("/pedido-byId/" + this.unPedidoModeloConPieza.getId().toString() + "/efectivizar");
		
		Pedido pedido = pedidoDao.findByID(this.unPedidoModeloConPieza.getId());
		Assert.assertTrue(pedido.isEfectivo());
	}
	
	@Test
	public void cancelarPedido() throws IOException {
		router.get("/pedido-byId/" + this.unPedidoModeloConPieza.getId().toString() + "/cancelar");
		
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
