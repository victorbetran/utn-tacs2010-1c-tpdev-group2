package org.utn.tacs.tp.group2.db.pedido;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class AbmDePedidosTest extends PedidoTest {

	private Pedido pedidoPersistido;

	private Pedido pedidoPersistidoFromDao;

	@Override
	public void setUp() {
		super.setUp();

		this.pedidoPersistido = new Pedido();
		this.dao.save(this.pedidoPersistido);

		this.pedidoPersistidoFromDao = this.dao.findByID(pedidoPersistido.getId());
	}

	@Test
	public void persistirPedido() {
		Assert.assertTrue(this.dao.isPersisted(this.pedidoPersistido));
	}

	@Test
	public void eliminarPedidoPersistido() {
		this.dao.remove(pedidoPersistido);
		Assert.assertFalse(this.dao.isPersisted(pedidoPersistido));
	}

	@Test
	public void modificarPedidoPersitido() {
		Assert.assertTrue(pedidoPersistidoFromDao.getEstado().isEnCurso());
		pedidoPersistidoFromDao.cancelar();
		Pedido pedidoModified = this.dao.findByID(pedidoPersistidoFromDao.getId());
		Assert.assertTrue(pedidoModified.getEstado().isCancelado());
	}
}
