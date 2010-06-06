package org.utn.tacs.tp.group2.service.pedido;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PedidoService;
import org.utn.tacs.tp.group2.service.implementation.PedidoServiceImpl;
import org.utn.tacs.tp.group2.service.pieza.PiezaDAOMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ComportamientoDePedidoServiceTest {

	@Autowired
	private PedidoService pedidoService;
	
	private PedidoDAOMock pedidoDAO;
	private PiezaDAOMock piezaDAO;
	
	Pedido pedidoSinPiezas;
	Pedido pedidoConUnaPieza;
	Pieza piezaAgregadaAPedido;

	@Before
	public void setUp() {
		pedidoDAO = new PedidoDAOMock();
		piezaDAO = new PiezaDAOMock();
		((PedidoServiceImpl)this.pedidoService).setPedidoDAO(pedidoDAO);
		((PedidoServiceImpl)this.pedidoService).setPiezaDAO(piezaDAO);

		pedidoSinPiezas = Pedido.createPedido();
		pedidoConUnaPieza = Pedido.createPedido();

		piezaAgregadaAPedido = new Pieza("CODE!", new BigDecimal(12), Moneda.Pesos);
		pedidoConUnaPieza.addPieza(piezaAgregadaAPedido);
		
		piezaDAO.save(piezaAgregadaAPedido);
		
		pedidoDAO.save(pedidoSinPiezas);
		pedidoDAO.save(pedidoConUnaPieza);

	}

	@Test
	public void consultarPedidoPorId() {
		Assert.assertEquals(this.pedidoSinPiezas, this.pedidoService.getPedidoById(this.pedidoSinPiezas.getId().toString()));
	}

	@Test
	public void consultarPedidoInexistente() {
		Assert.assertNull(this.pedidoService.getPedidoById(Pedido.createPedido().getId().toString()));
	}
	
	@Test
	public void crearPedido() {
		Pedido nuevoPedido = this.pedidoService.crearPedido();
		Assert.assertTrue(this.pedidoDAO.isPersisted(nuevoPedido));
	}
	
	@Test
	public void agregarPiezaAPedidoSinPiezas() {
		Pieza unaPieza = new Pieza("AZF-2221", new BigDecimal(64), Moneda.Pesos);
		this.piezaDAO.save(unaPieza);
		
		Assert.assertEquals(this.pedidoSinPiezas,this.pedidoService.agregarPiezaAlPedido(this.pedidoSinPiezas.getId().toString(), unaPieza.getId().toString()));
		
		Pedido pedidoGivenFromDao = this.pedidoDAO.findByID(this.pedidoSinPiezas.getId());
		
		Assert.assertEquals(1,pedidoGivenFromDao.getPiezas().size());
		Assert.assertTrue(pedidoGivenFromDao.getPiezas().contains(unaPieza));
	}

	@Test
	public void agregarPiezaAPedidoConUnaPieza() {
		Pieza unaPieza = new Pieza("AZF-2221", new BigDecimal(64), Moneda.Pesos);
		this.piezaDAO.save(unaPieza);
		Assert.assertEquals(this.pedidoConUnaPieza,this.pedidoService.agregarPiezaAlPedido(this.pedidoConUnaPieza.getId().toString(), unaPieza.getId().toString()));
		
		Pedido pedidoGivenFromDao = this.pedidoDAO.findByID(this.pedidoConUnaPieza.getId());
		
		Assert.assertEquals(2,pedidoGivenFromDao.getPiezas().size());
		Assert.assertTrue(pedidoGivenFromDao.getPiezas().contains(unaPieza));
		Assert.assertTrue(pedidoGivenFromDao.getPiezas().contains(piezaAgregadaAPedido));
	}
	
	@Test
	public void cancelarUnPedido(){
		Assert.assertTrue(!this.pedidoConUnaPieza.isCancelado());
		
		Assert.assertEquals(this.pedidoConUnaPieza,this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString()));
		
		Assert.assertTrue(this.pedidoConUnaPieza.isCancelado());
	}
	
	@Test
	public void cancelarUnPedidoQueEstabaCancelado(){
		// TODO: Hacer, deberia devolver null ?
	}
	
	@Test
	public void efectivizarUnPedido() {
		Assert.assertTrue(!this.pedidoConUnaPieza.isEfectivo());
		
		this.pedidoService.efectivizarPedido(this.pedidoConUnaPieza.getId().toString());
		
		Assert.assertTrue(this.pedidoConUnaPieza.isEfectivo());
	}

	@Test
	public void efectivizarUnPedidoQueEstabaCancelado() {
		// TODO: Hacer, deberia devolver null ?
	}
	
	//********************************************
	//** GETTER & SETTER
	//********************************************
	
	public PedidoService getPedidoService() {
		return pedidoService;
	}
	
	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
}
//	@Transactional
//	@Test
//	public void cancelarPedido() {
//		pedidoService.cancelarPedido(pedido1);
//		Assert.assertTrue(pedido1.isCancelado());
//	}
	
//	@Transactional
//	@Test
//	public void efectivizarPedido() {
//		pedido1.addPieza(pieza1);
//		pedidoService.efectivizarPedido(pedido1);
//		Assert.assertTrue(pedido1.isEfectivo());
//	}

	// TODO: Ver como lograr hacer andar este test... mismo problema que en el de pieza
//	@Transactional
	// @Test
//	public void consultarPedidoById() {
//		Pedido p = pedidoService.newPedido();
//		Pedido pedidoLoaded = pedidoService.getPedidoById(p.getId());
//		Assert.assertEquals(p, pedidoLoaded);
//	}
//
//	@Transactional
//	@Test
//	public void consultarPedidosByEstadoEnCurso() {
//		List<Pedido> pedidos = pedidoService.loadPedidosByEstado(EstadoPedido.getEnCurso());
//		Assert.assertEquals(5, pedidos.size());
//		Assert.assertTrue(pedidos.contains(pedido1));
//		Assert.assertTrue(pedidos.contains(pedido2));
//		Assert.assertTrue(pedidos.contains(pedido3));
//		Assert.assertTrue(pedidos.contains(pedido4));
//		Assert.assertTrue(pedidos.contains(pedido5));
//	}
//
//	@Transactional
//	@Test
//	public void consultarPedidosByEstadoCancelado() {
//		pedido1.cancelar();
//		pedido2.cancelar();
//		List<Pedido> pedidos = pedidoService.loadPedidosByEstado(EstadoPedido.getCancelado());
//		Assert.assertEquals(2, pedidos.size());
//		Assert.assertTrue(pedidos.contains(pedido1));
//		Assert.assertTrue(pedidos.contains(pedido2));
//	}
//
//	@Transactional
//	@Test
//	public void consultarPedidosByEstadoEfectivo() {
//		List<Pedido> pedidos = pedidoService.loadPedidosByEstado(EstadoPedido.getEfectivo());
//		Assert.assertEquals(0, pedidos.size());
//	}


