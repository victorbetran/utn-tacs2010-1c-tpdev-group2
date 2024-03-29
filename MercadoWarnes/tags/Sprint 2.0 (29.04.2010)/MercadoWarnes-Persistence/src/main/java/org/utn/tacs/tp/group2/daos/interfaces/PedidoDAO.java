package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.implementations.PedidoDAOImpl;
import org.utn.tacs.tp.group2.pedido.Pedido;

public abstract class PedidoDAO extends AbstractDao {

	private static PedidoDAO instance = null;

	public static PedidoDAO getInstance() {
		if (instance == null) {
			instance = new PedidoDAOImpl();
		}

		return instance;
	}

	/**
	 * Devuelve el pedido cuyo ID corresponde al id pasado al mensaje.
	 */
	public abstract Pedido findByID(Long id);

	/**
	 * Guarda un pedido en la BD.
	 */
	public abstract void save(Pedido pedido);

	/**
	 * Indica si el pedido se ha persistido.
	 * 
	 * @param pedido
	 * @return
	 */
	public abstract Boolean isPersisted(Pedido pedido);

	/**
	 * Elimina el pedido de la DB.
	 * 
	 * @param pedido
	 */
	public abstract void delete(Pedido pedido);

	/**
	 * Carga todos los pedido con ese estado.
	 * 
	 * @return una lista de pedidos
	 */
	public abstract List<Pedido> findByEstado(String tipoEstado);

}
