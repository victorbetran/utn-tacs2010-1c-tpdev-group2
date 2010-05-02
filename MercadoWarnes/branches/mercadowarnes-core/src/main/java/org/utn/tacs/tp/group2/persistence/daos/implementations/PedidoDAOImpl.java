package org.utn.tacs.tp.group2.persistence.daos.implementations;

import java.util.List;

import org.utn.tacs.tp.group2.domain.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.domain.pedido.Pedido;
import org.utn.tacs.tp.group2.persistence.daos.interfaces.PedidoDAO;

public class PedidoDAOImpl extends PedidoDAO {

	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		return getQueryHandler().setBody("from Pedido as pedido inner join fetch pedido.estado WHERE pedido.estado = :est")
								.addParameter("est", estado)
								.getResults();
	}

}
