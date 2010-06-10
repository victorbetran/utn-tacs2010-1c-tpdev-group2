package org.utn.tacs.tp.group2.service.pedido;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
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

		piezaAgregadaAPedido = new Pieza("CODE!", 12, Moneda.Pesos);
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
		Pieza unaPieza = new Pieza("AZF-2221", 64, Moneda.Pesos);
		this.piezaDAO.save(unaPieza);
		
		Assert.assertEquals(this.pedidoSinPiezas,this.pedidoService.agregarPiezaAlPedido(this.pedidoSinPiezas.getId().toString(), unaPieza.getId().toString()));
		
		Pedido pedidoGivenFromDao = this.pedidoDAO.findByID(this.pedidoSinPiezas.getId());
		
		Assert.assertEquals(1,pedidoGivenFromDao.getPiezas().size());
		Assert.assertTrue(pedidoGivenFromDao.getPiezas().contains(unaPieza));
	}

	@Test
	public void agregarPiezaAPedidoConUnaPieza() {
		Pieza unaPieza = new Pieza("AZF-2221", 64, Moneda.Pesos);
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
		this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString());
		Assert.assertNull(this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString()));
	}
	
	@Test
	public void efectivizarUnPedido() {
		Assert.assertTrue(!this.pedidoConUnaPieza.isEfectivo());
		
		this.pedidoService.efectivizarPedido(this.pedidoConUnaPieza.getId().toString());
		
		Assert.assertTrue(this.pedidoConUnaPieza.isEfectivo());
	}

	@Test
	public void efectivizarUnPedidoQueEstabaCancelado() {
		this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString());
		Assert.assertNull(this.pedidoService.efectivizarPedido(this.pedidoConUnaPieza.getId().toString()));
	}
	
	@Test
	public void consultarPedidosPorEstado() {
		List<Pedido> pedidos = this.pedidoService.getPedidosByEstado(EstadoPedido.getEnCurso().toString());
		
		Assert.assertEquals(2,pedidos.size());
		Assert.assertTrue(pedidos.contains(this.pedidoConUnaPieza));
		Assert.assertTrue(pedidos.contains(this.pedidoSinPiezas));
	}

	@Test
	public void consultarPedidosPorEstadoSinResultado() {
		List<Pedido> pedidos = this.pedidoService.getPedidosByEstado(EstadoPedido.getCancelado().toString());
		
		Assert.assertEquals(0,pedidos.size());
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
