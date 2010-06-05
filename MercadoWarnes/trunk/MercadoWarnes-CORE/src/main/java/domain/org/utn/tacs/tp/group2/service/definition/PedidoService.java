package org.utn.tacs.tp.group2.service.definition;

import java.util.List;

import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

/**
 * Interfaz del Servicio de Pedidos.
 */
public interface PedidoService {

	Pedido newPedido();

	Pedido loadPedidoById(Long id);
	
	void delete(Pedido pedido);

	void save(Pedido pedido);

	List<Pedido> loadPedidosByEstado(EstadoPedido estado);

	void efectivizarPedido(Pedido pedido);

	void cancelarPedido(Pedido pedido);

}
