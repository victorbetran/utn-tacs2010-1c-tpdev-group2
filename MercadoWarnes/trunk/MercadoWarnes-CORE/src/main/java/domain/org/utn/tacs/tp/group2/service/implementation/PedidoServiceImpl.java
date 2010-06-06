package org.utn.tacs.tp.group2.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.service.definition.PedidoService;

/**
 * Implementaci�n del Servicio de Pedidos.
 */
@Transactional
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private PiezaDAO piezaDAO;
	
	public Pedido crearPedido() {
		return null;
	}

	public void agregarPieza(Long pedidoId, Long piezaId) {
		
	}

	public void cancelarPedido(Pedido pedido) {
		
	}

	public void efectivizarPedido(Pedido pedido) {
		
	}

	public Pedido getPedidoById(Long id) {
		return null;
	}

	public List<Pedido> loadPedidosByEstado(String estadoDePedido) {
		return null;
	}
	
	//********************************************
	//** GETTER & SETTER
	//********************************************
	
	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}
	
	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public void setPiezaDAO(PiezaDAO piezaDAO) {
		this.piezaDAO = piezaDAO;
	}
	
	public PiezaDAO getPiezaDAO() {
		return piezaDAO;
	}
	
}
