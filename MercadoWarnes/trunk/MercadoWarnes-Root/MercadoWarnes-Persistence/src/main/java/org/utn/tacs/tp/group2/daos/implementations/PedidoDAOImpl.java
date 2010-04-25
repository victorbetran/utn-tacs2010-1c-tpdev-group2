package org.utn.tacs.tp.group2.daos.implementations;

import java.util.List;

import org.hibernate.Session;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;


public class PedidoDAOImpl extends AbstractDao implements PedidoDAO{

	public List<Pedido> findByEstado(EstadoPedido estado) {
		return null;
	}

	private Pedido pedido;
	public Pedido findByID(final Long id) {
		doExecute(new Command(){

			public void execute(Session session) throws Exception {
				pedido = (Pedido) session.load(Pedido.class, id);
			}
			
		});
		return pedido;
	}

	public void save(final Pedido pedido) {
		doExecute(new Command() {
			
			public void execute(Session session) throws Exception {
				session.save(pedido);
			}
			
		});
	}

}
