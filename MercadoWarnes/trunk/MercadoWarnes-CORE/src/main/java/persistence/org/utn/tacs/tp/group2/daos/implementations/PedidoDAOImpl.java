package org.utn.tacs.tp.group2.daos.implementations;

import java.util.List;

import org.utn.tacs.tp.group2.daos.exceptions.PedidoInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoDAOImpl extends PedidoDAO {

	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		return getQueryHandler().setBody("from Pedido as pedido WHERE pedido.estado = :est")
								.addParameter("est", estado)
								.getResults();
	}

	@Override
	protected RuntimeException getNotFoundObjectException(Long id) {
		return new PedidoInexistenteException("No existe una pedido con el ID: '" + id + "'");
	}

}
