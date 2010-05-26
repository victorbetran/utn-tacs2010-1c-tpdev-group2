package org.utn.tacs.tp.group2.persistence.pedido;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class ConsultaDePedidosTest extends PedidoTest {

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

	}

	// ********************************************
	// ** TEST METHODS
	// ********************************************
	/**
	 * Consulta un pedido existente en la BD por su ID
	 */
	@Test
	public void consultarPedidoPorIDTest() {
		Pedido pedidoFromDao = dao.findByID(pedidoPersistido1.getId());
		Assert.assertEquals("El Pedido persistido no coincide con el accedido.", pedidoPersistido1,	pedidoFromDao);
	}

	/**
	 * Consulta pedidos que tiene de estado en curso.
	 */
	@Test
	public void consultarPedidosEnCurso() {
		assertList(dao.findByEstado(EstadoPedido.getEnCurso()), this.pedidoPersistido1,this.pedidoPersistido2);
	}
	
	/**
	 * Consulta pedidos que tiene de estado cancelado.
	 */
	@Test
	public void consultarPedidosPorEstadoCancelado() {
		this.pedidoPersistido1.cancelar();
		this.pedidoPersistido2.cancelar();
		assertList(dao.findByEstado(EstadoPedido.getCancelado()), this.pedidoPersistido1, this.pedidoPersistido2);
	}

	/**
	 * Consulta pedidos que tiene de estado efectivo.
	 */
	@Test
	public void consultarPedidosPorEstadoEfectivo() {
		List<Pedido> pedidos = dao.findByEstado(EstadoPedido.getEfectivo());
		Assert.assertEquals(0, pedidos.size());
	}
	
	// *******************************************************************************
	// *** HELPER
	// *******************************************************************************
	
	private void assertList(List<Pedido> toBeChecked,Pedido ...expected) {
		int qty = expected.length;
		
		Assert.assertEquals("La cantidad de elementos en la lista no es la esperada.",qty, toBeChecked.size());
		for (Pedido pedidoExpected : expected) {
			Assert.assertTrue("Uno de los elemenos esperados no se encuentra en el conjunto.",toBeChecked.contains(pedidoExpected));
		}
	}
	
}
