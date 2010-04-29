package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoDAOImpl extends PedidoDAO {

	private Pedido pedido;

	// ********************************************
	// ** PUBIC METHODS
	// ********************************************
	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		return null;
	}

	@Override
	public Pedido findByID(final Long id) {

		doExecute(new Command() {

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

	private Boolean value;

	@Override
	public Boolean isPersisted(final Pedido pedido) {

		doExecute(new Command() {

			public void execute(Session session) {
				value = session.contains(pedido);
			}

		});
		return value;
	}

	@Override
	public void delete(final Pedido pedido) {
		doExecute(new Command() {

			public void execute(Session session) {
				session.delete(pedido);
			}

		});

	}

	@Override
	public List<Pedido> findByEstadoEnCurso() {
		final List<Pedido> resultado = new ArrayList<Pedido>();
		doExecute(new Command() {

			@SuppressWarnings("unchecked")
			public void execute(Session session) {
				Query q = session.createQuery("FROM Pedido WHERE estado.tipoEstado = :tipo");
				String tipo = null;
				q.setParameter("tipo", "EN_CURSO");
				resultado.addAll(q.list());

			}
		});

		return resultado;
	}

}
