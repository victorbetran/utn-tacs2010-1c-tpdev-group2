package org.utn.tacs.tp.group2.service.pedido;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoCanceladoException;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoSinPiezasException;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PedidoService;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;
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
	
	PedidoDTO pedidoSinPiezasDTO;
	PedidoDTO pedidoConUnaPiezaDTO;

	@Before
	public void setUp() {
		pedidoDAO = new PedidoDAOMock();
		piezaDAO = new PiezaDAOMock();
		this.pedidoService.setPedidoDAO(pedidoDAO);
		this.pedidoService.setPiezaDAO(piezaDAO);

		pedidoSinPiezas = Pedido.create();
		pedidoConUnaPieza = Pedido.create();

		piezaAgregadaAPedido = new Pieza("CODE!", 12, Moneda.Pesos);
		pedidoConUnaPieza.addPieza(piezaAgregadaAPedido);
		
		piezaDAO.save(piezaAgregadaAPedido);
		
		pedidoDAO.save(pedidoSinPiezas);
		pedidoDAO.save(pedidoConUnaPieza);

		this.pedidoSinPiezasDTO = new PedidoDTO(this.pedidoSinPiezas);
		this.pedidoConUnaPiezaDTO = new PedidoDTO(this.pedidoConUnaPieza);
	}

	@Test
	public void consultarPedidoPorId() {
		Assert.assertEquals(this.pedidoSinPiezasDTO, this.pedidoService.getPedidoById(this.pedidoSinPiezas.getId().toString()));
	}

	@Test
	public void consultarPedidoInexistente() {
		Assert.assertNull(this.pedidoService.getPedidoById(Pedido.create().getId().toString()));
	}
	
//	@Test
//	public void crearPedido() {
//		Pedido nuevoPedido = this.pedidoService.crearPedido();
//		Assert.assertTrue(this.pedidoDAO.isPersisted(nuevoPedido));
//	}
	
	@Test
	public void agregarPiezaAPedidoSinPiezas() {
		Pieza unaPieza = new Pieza("AZF-2221", 64, Moneda.Pesos);
		this.piezaDAO.save(unaPieza);
		
		this.pedidoService.agregarPiezaAlPedido(this.pedidoSinPiezas.getId().toString(), unaPieza.getId().toString());
		
		Pedido pedidoGivenFromDao = this.pedidoDAO.findByID(this.pedidoSinPiezas.getId());
		
		Assert.assertEquals(1,pedidoGivenFromDao.getPiezas().size());
		Assert.assertTrue(pedidoGivenFromDao.getPiezas().contains(unaPieza));
	}

	@Test
	public void agregarPiezaAPedidoConUnaPieza() {
		Pieza unaPieza = new Pieza("AZF-2221", 64, Moneda.Pesos);
		this.piezaDAO.save(unaPieza);
		this.pedidoService.agregarPiezaAlPedido(this.pedidoConUnaPieza.getId().toString(), unaPieza.getId().toString());
		
		Pedido pedidoGivenFromDao = this.pedidoDAO.findByID(this.pedidoConUnaPieza.getId());
		
		Assert.assertEquals(2,pedidoGivenFromDao.getPiezas().size());
		Assert.assertTrue(pedidoGivenFromDao.getPiezas().contains(unaPieza));
		Assert.assertTrue(pedidoGivenFromDao.getPiezas().contains(piezaAgregadaAPedido));
	}
	
	@Test
	public void cancelarUnPedido(){
		Assert.assertTrue(!this.pedidoConUnaPieza.isCancelado());
		
		this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString());
		
		Assert.assertTrue(this.pedidoConUnaPieza.isCancelado());
	}
	
	@Test(expected=PedidoCanceladoException.class)
	public void cancelarUnPedidoQueEstabaCancelado(){
		this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString());
		this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString());
	}
	
	@Test
	public void efectivizarUnPedido() {
		Assert.assertTrue(!this.pedidoConUnaPieza.isEfectivo());
		
		this.pedidoService.efectivizarPedido(this.pedidoConUnaPieza.getId().toString());
		
		Assert.assertTrue(this.pedidoConUnaPieza.isEfectivo());
	}

	@Test(expected=PedidoSinPiezasException.class)
	public void efectivizarUnPedidoQueEstabaCancelado() {
		this.pedidoService.cancelarPedido(this.pedidoConUnaPieza.getId().toString());
		this.pedidoService.efectivizarPedido(this.pedidoConUnaPieza.getId().toString());
	}
	
	@Test
	public void consultarPedidosPorEstado() {
		List<PedidoDTO> pedidos = this.pedidoService.getPedidosByEstado(EstadoPedido.getEnCurso().toString());
		
		Assert.assertEquals(2,pedidos.size());
		Assert.assertTrue(pedidos.contains(this.pedidoConUnaPiezaDTO));
		Assert.assertTrue(pedidos.contains(this.pedidoSinPiezasDTO));
	}

	@Test
	public void consultarPedidosPorEstadoSinResultado() {
		List<PedidoDTO> pedidos = this.pedidoService.getPedidosByEstado(EstadoPedido.getCancelado().toString());
		
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
