package org.utn.tacs.tp.group2.persistence.pedido;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.exceptions.PedidoInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pedido.PedidoBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ConsultaDePedidosTest {

	@Autowired
	private PedidoDAO dao;
	
	Pedido pedidoPersistido1;
	Pedido pedidoPersistido2;

	Pedido pedidoPersistidoCancelado1;
	Pedido pedidoPersistidoCancelado2;

	@Before
	public void setUp() {

		this.pedidoPersistido1 = new PedidoBuilder().Build();
		this.dao.save(this.pedidoPersistido1);

		this.pedidoPersistido2 = new PedidoBuilder().Build();
		this.dao.save(this.pedidoPersistido2);

	}

	// ********************************************
	// ** TEST METHODS
	// ********************************************

	/**
	 * Consulta un pedido existente en la BD por su ID
	 */
	@Test
	@Transactional
	public void consultarPedidoPorIDTest() {
		Pedido pedidoFromDao = dao.findByID(pedidoPersistido1.getId());
		Assert.assertEquals("El Pedido persistido no coincide con el accedido.", pedidoPersistido1,	pedidoFromDao);
	}

	/**
	 * Consultar por un ID inexistente
	 */
	@Transactional
	@Test(expected = PedidoInexistenteException.class)
	public void consultarPiezaInexistenteTest() {
		dao.findByID(new Pedido().getId());
	}
	
	/**
	 * Consulta pedidos que tiene de estado en curso.
	 */
	@Test
	@Transactional
	public void consultarPedidosEnCurso() {
		assertList(dao.findByEstado(EstadoPedido.getEnCurso()), this.pedidoPersistido1,this.pedidoPersistido2);
	}
	
	/**
	 * Consulta pedidos que tiene de estado cancelado.
	 */
	@Test
	@Transactional
	public void consultarPedidosPorEstadoCancelado() {
		this.pedidoPersistido1.cancelar();
		this.pedidoPersistido2.cancelar();
		assertList(dao.findByEstado(EstadoPedido.getCancelado()), this.pedidoPersistido1, this.pedidoPersistido2);
	}

	/**
	 * Consulta pedidos que tiene de estado efectivo.
	 */
	@Test
	@Transactional
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
