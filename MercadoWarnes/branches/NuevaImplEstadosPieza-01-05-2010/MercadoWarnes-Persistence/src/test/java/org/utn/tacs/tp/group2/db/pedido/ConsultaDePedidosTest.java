package org.utn.tacs.tp.group2.db.pedido;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class ConsultaDePedidosTest extends PedidoTest {

	private static final int NUMERO_PEDIDOS_ENCURSO = 2;

	private static final int NUMERO_PEDIDOS_CANCELADOS = 2;

	private static final int NUMERO_PEDIDOS_EFECTIVOS = 0;
	
	Pedido pedidoPersistido1;

	Pedido pedidoPersistido2;

	Pedido pedidoPersistidoCancelado1;

	Pedido pedidoPersistidoCancelado2;

	@Override
	public void setUp() {
		super.setUp();

		this.pedidoPersistido1 = new Pedido();
		this.dao.save(this.pedidoPersistido1);

		this.pedidoPersistido2 = new Pedido();
		this.dao.save(this.pedidoPersistido2);

		this.pedidoPersistidoCancelado1 = new Pedido();
		this.pedidoPersistidoCancelado1.cancelar();
		this.dao.save(this.pedidoPersistidoCancelado1);

		this.pedidoPersistidoCancelado2 = new Pedido();
		this.pedidoPersistidoCancelado2.cancelar();
		this.dao.save(this.pedidoPersistidoCancelado2);
	}

	// ********************************************
	// ** TEST METHODS
	// ********************************************
	/**
	 * Consulta un pedido existente en la BD por su ID
	 */
	@Test
	public void consultarPedidoPorId() {
		Pedido pedidoObtenidoConDao = dao.findByID(pedidoPersistido1.getId());
		Assert.assertEquals("El Pedido persistido no coincide con el accedido.", pedidoPersistido1,
				pedidoObtenidoConDao);
	}

	/**
	 * Consulta pedidos que tiene de estado en curso.
	 */
	@Test
	public void consultarPedidosPorEstadoEnCurso() {
		List<Pedido> pedidos = dao.findByEstado("EN_CURSO");
		Assert.assertEquals(NUMERO_PEDIDOS_ENCURSO, pedidos.size());
		Assert.assertTrue(pedidos.contains(pedidoPersistido1));
		Assert.assertTrue(pedidos.contains(pedidoPersistido2));
	}
	
	/**
	 * Consulta pedidos que tiene de estado cancelado.
	 */
	@Test
	public void consultarPedidosPorEstadoCancelado() {
		List<Pedido> pedidos = dao.findByEstado("CANCELADO");
		Assert.assertEquals(NUMERO_PEDIDOS_CANCELADOS, pedidos.size());
		Assert.assertTrue(pedidos.contains(pedidoPersistidoCancelado1));
		Assert.assertTrue(pedidos.contains(pedidoPersistidoCancelado2));
	}

	/**
	 * Consulta pedidos que tiene de estado efectivo.
	 */
	@Test
	public void consultarPedidosPorEstadoEfectivo() {
		List<Pedido> pedidos = dao.findByEstado("EFECTIVO");
		Assert.assertEquals(NUMERO_PEDIDOS_EFECTIVOS, pedidos.size());
	}
}
