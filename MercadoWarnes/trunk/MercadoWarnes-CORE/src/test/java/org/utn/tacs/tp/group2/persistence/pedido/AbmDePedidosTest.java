package org.utn.tacs.tp.group2.persistence.pedido;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pedido.PedidoBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class AbmDePedidosTest {

	@Autowired
	private PedidoDAO dao;
	
	private Pedido pedidoPersistido;
	private Pedido pedidoPersistidoFromDao;

	@Before
	public void setUp() {
		this.pedidoPersistido = new PedidoBuilder().Build();
		this.dao.save(this.pedidoPersistido);

		this.pedidoPersistidoFromDao = this.dao.findByID(pedidoPersistido.getId());
	}

	@Test
	@Transactional
	public void persistirPedido() {
		Assert.assertTrue(this.dao.isPersisted(this.pedidoPersistido));
	}

	@Test
	@Transactional
	public void eliminarPedidoPersistido() {
		this.dao.remove(pedidoPersistido);
		Assert.assertFalse(this.dao.isPersisted(pedidoPersistido));
	}

	@Test
	@Transactional
	public void modificarPedidoPersitido() {
		Assert.assertTrue(pedidoPersistidoFromDao.getEstado().isEnCurso());
		pedidoPersistidoFromDao.cancelar();
		Pedido pedidoModified = this.dao.findByID(pedidoPersistidoFromDao.getId());
		Assert.assertTrue(pedidoModified.getEstado().isCancelado());
	}

}
