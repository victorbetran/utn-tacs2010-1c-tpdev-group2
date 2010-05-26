package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.implementations.PedidoDAOImpl;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public abstract class PedidoDAO extends AbstractDao<Pedido> {

	private static PedidoDAO instance = null;
	
	public static PedidoDAO getInstance() {
		if (instance == null) {
			instance = new PedidoDAOImpl();
		}

		return instance;
	}

	@Override
	protected Class<Pedido> getGenericClass() {
		return Pedido.class;
	}
	

	/**
	 * Carga todos los pedido con ese estado.
	 * 
	 * @return una lista de pedidos
	 */
	public abstract List<Pedido> findByEstado(EstadoPedido tipoEstado);

}
