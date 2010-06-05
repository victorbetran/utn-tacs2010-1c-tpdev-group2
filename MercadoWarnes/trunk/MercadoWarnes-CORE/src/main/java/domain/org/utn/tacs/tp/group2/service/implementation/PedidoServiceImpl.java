package org.utn.tacs.tp.group2.service.implementation;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pedido.PedidoBuilder;
import org.utn.tacs.tp.group2.service.definition.PedidoService;

/**
 * Implementaci�n del Servicio de Pedidos.
 */
@Transactional
public class PedidoServiceImpl implements PedidoService {

	private PedidoDAO pedidoDAO;

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public void save(Pedido pedido) {
		pedidoDAO.save(pedido);
	}

	public void delete(Pedido pedido) {
		pedidoDAO.remove(pedido);
	}

	public Pedido newPedido() {
		Pedido pedido = new PedidoBuilder().Build();
		this.save(pedido);
		return pedido;
	}

	public void cancelarPedido(Pedido pedido) {
		pedido.cancelar();
	}

	public void efectivizarPedido(Pedido pedido) {
		pedido.efectivizar();
	}

	@Transactional(readOnly = true)
	public Pedido loadPedidoById(Long id) {
		return pedidoDAO.findByID(id);
	}

	@Transactional(readOnly = true)
	public List<Pedido> loadPedidosByEstado(EstadoPedido estado) {
		return pedidoDAO.findByEstado(estado);
	}

}
