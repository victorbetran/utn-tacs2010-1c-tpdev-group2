package org.utn.tacs.tp.group2.daos.interfaces;

public interface DAOFactory {

	/**
	 * Retorna un DAO que permite el acceso a las Piezas.
	 */
	PiezaDAO getPiezaDAO();
	
	/**
	 * Retorna un DAO que permite el acceso a los Pedidos.
	 */
	PedidoDAO getPedidoDAO();
}
