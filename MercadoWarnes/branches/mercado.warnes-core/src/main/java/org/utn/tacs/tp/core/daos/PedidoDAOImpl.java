package org.utn.tacs.tp.core.daos;

import java.util.List;

import org.utn.tacs.tp.core.domain.EstadoPedido;
import org.utn.tacs.tp.core.domain.Pedido;

public class PedidoDAOImpl extends PedidoDAO {

	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		return getQueryHandler().setBody("from Pedido as pedido inner join fetch pedido.estado WHERE pedido.estado = :est")
								.addParameter("est", estado)
								.getResults();
	}

}
