package org.utn.tacs.tp.group2.service.pedido;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pedido.PedidoBuilder;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PedidoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PedidoServiceTest {

	@Autowired
	private PedidoService pedidoService;

	Pedido pedido1;
	Pedido pedido2;
	Pedido pedido3;
	Pedido pedido4;
	Pedido pedido5;

	Pieza pieza1;
	Pieza pieza2;

	@Before
	public void setUp() {
		pedido1 = new PedidoBuilder().Build();
		pedido2 = new PedidoBuilder().Build();
		pedido3 = new PedidoBuilder().Build();
		pedido4 = new PedidoBuilder().Build();
		pedido5 = new PedidoBuilder().Build();

		pedidoService.save(pedido1);
		pedidoService.save(pedido2);
		pedidoService.save(pedido3);
		pedidoService.save(pedido4);
		pedidoService.save(pedido5);

		pieza1 = new Pieza("A-123", new BigDecimal(30), Moneda.Dolares);
		pieza2 = new Pieza("B-369", new BigDecimal(30), Moneda.Pesos);

	}

	@Transactional
	@Test
	public void crearPedido() {
		Pedido p = pedidoService.newPedido();
		Assert.assertNotNull(p);
	}

	@Transactional
	@Test
	public void cancelarPedido() {
		pedidoService.cancelarPedido(pedido1);
		Assert.assertTrue(pedido1.isCancelado());
	}
	
	@Transactional
	@Test
	public void efectivizarPedido() {
		pedido1.addPieza(pieza1);
		pedidoService.efectivizarPedido(pedido1);
		Assert.assertTrue(pedido1.isEfectivo());
	}

	// TODO: Ver como lograr hacer andar este test... mismo problema que en el de pieza
//	@Transactional
	// @Test
	public void consultarPedidoById() {
		Pedido p = pedidoService.newPedido();
		Pedido pedidoLoaded = pedidoService.loadPedidoById(p.getId());
		Assert.assertEquals(p, pedidoLoaded);
	}

	@Transactional
	@Test
	public void consultarPedidosByEstadoEnCurso() {
		List<Pedido> pedidos = pedidoService.loadPedidosByEstado(EstadoPedido.getEnCurso());
		Assert.assertEquals(5, pedidos.size());
		Assert.assertTrue(pedidos.contains(pedido1));
		Assert.assertTrue(pedidos.contains(pedido2));
		Assert.assertTrue(pedidos.contains(pedido3));
		Assert.assertTrue(pedidos.contains(pedido4));
		Assert.assertTrue(pedidos.contains(pedido5));
	}

	@Transactional
	@Test
	public void consultarPedidosByEstadoCancelado() {
		pedido1.cancelar();
		pedido2.cancelar();
		List<Pedido> pedidos = pedidoService.loadPedidosByEstado(EstadoPedido.getCancelado());
		Assert.assertEquals(2, pedidos.size());
		Assert.assertTrue(pedidos.contains(pedido1));
		Assert.assertTrue(pedidos.contains(pedido2));
	}

	@Transactional
	@Test
	public void consultarPedidosByEstadoEfectivo() {
		List<Pedido> pedidos = pedidoService.loadPedidosByEstado(EstadoPedido.getEfectivo());
		Assert.assertEquals(0, pedidos.size());
	}

}
