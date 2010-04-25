package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.implementations.PedidoDAOImpl;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;


public abstract class PedidoDAO extends AbstractDao{

	private static PedidoDAO instance = null;
	public static PedidoDAO getInstance(){
		if(instance == null){
			instance = new PedidoDAOImpl();
		}
		
		return instance;
	}
	
	/**
	 * Devuelve un listado con los pedidos que se encuentran en un determinado estado.
	 */
	public abstract List<Pedido> findByEstado(EstadoPedido estado);

	/**
	 * Devuelve el pedido cuyo ID corresponde al id pasado al mensaje.
	 */
	public abstract Pedido findByID(Long id);

	/**
	 * Guarda un pedido en la BD.
	 */
	public abstract void save(Pedido pedido);
	
}
