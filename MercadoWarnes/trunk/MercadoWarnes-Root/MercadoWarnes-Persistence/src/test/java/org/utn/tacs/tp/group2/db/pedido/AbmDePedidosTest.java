package org.utn.tacs.tp.group2.db.pedido;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;

/**
 * Tests sobre las operación de alta, baja, modificación y recupero de pedidos.
 */
public class AbmDePedidosTest extends PedidoTest {

	private Pedido pedidoPersistido;

	private Pedido pedidoPersistidoLoaded;

	@Override
	public void setUp() {
		super.setUp();

		this.pedidoPersistido = new Pedido();
		this.dao.save(this.pedidoPersistido);

		this.pedidoPersistidoLoaded = this.dao.findByID(pedidoPersistido.getId());
	}

	/**
	 * Persiste un pedido y verifica que se haya guardado en la DB.
	 */
	@Test
	public void persistirPedido() {
		Pedido pedido = new Pedido();
		this.dao.save(pedido);
		Assert.assertTrue(this.dao.isPersisted(pedido));
	}

	/**
	 * Elimina un pedido de la DB y verifica que sea ha eliminado.
	 */
	@Test
	public void eliminarPedidoPersistido() {
		this.dao.delete(pedidoPersistido);
		Assert.assertFalse(this.dao.isPersisted(pedidoPersistido));
	}

	/**
	 * Carga un pedido de la DB según su id.
	 */
	@Test
	public void cargarPedidoPersistidoById() {
		Pedido pedidoLoaded = this.dao.findByID(pedidoPersistido.getId());
		Assert.assertTrue(pedidoLoaded.equals(pedidoPersistido));
	}

	/**
	 * Un pedido en curso es cancelado y se verifica que sus estados se modifiquen.
	 */
	@Test
	public void modificarPedidoPersitido() {
		// El pedido está en curso
		Assert.assertTrue(pedidoPersistidoLoaded.getEstado().isEnCurso());
		// Modificación, el pedido se cancela
		pedidoPersistidoLoaded.cancelar();
		// Verificamos que el pedido traído de la DB haya cambiado su estado
		Pedido pedidoModified = this.dao.findByID(pedidoPersistidoLoaded.getId());
		Assert.assertTrue(pedidoModified.getEstado().isCancelado());
	}
}
