package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public interface PedidoDAO {

	/**
	 * Devuelve un listado con los pedidos que se encuentran en un determinado estado.
	 */
	List<Pedido> findByEstado(EstadoPedido estado);

	/**
	 * Devuelve el pedido cuyo ID corresponde al id pasado al mensaje.
	 */
	Pedido findByID(String id);

	/**
	 * Guarda un pedido en la BD.
	 */
	void save(Pedido pedido);
}
