package org.utn.tacs.tp.group2.daos.implementations;

import java.util.List;

import org.hibernate.Session;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;


public class PedidoDAOImpl extends PedidoDAO{

	private Pedido pedido;
	
	//********************************************
	//** PUBIC METHODS
	//********************************************
	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		return null;
	}

	@Override
	public Pedido findByID(final Long id) {
		
		doExecute(new Command(){

			public void execute(Session session) {
				pedido = (Pedido) session.load(Pedido.class, id);
			}
			
		});
		return pedido;
	}

	@Override
	public void save(final Pedido pedido) {
		doExecute(new Command() {
			
			public void execute(Session session) {
				session.save(pedido);
			}
			
		});
	}

}
