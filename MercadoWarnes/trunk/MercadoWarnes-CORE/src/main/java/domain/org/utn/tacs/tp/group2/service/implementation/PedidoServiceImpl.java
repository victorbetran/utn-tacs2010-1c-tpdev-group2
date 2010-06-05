package org.utn.tacs.tp.group2.service.implementation;

import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;

/**
 * Implementaci�n del Servicio de Pedidos.
 */
public class PedidoServiceImpl implements PedidoService {

	private PedidoDAO pedidoDAO;

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

}
