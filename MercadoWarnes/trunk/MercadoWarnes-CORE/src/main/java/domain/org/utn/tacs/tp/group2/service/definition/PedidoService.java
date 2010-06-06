package org.utn.tacs.tp.group2.service.definition;

import java.util.List;

import org.utn.tacs.tp.group2.pedido.Pedido;

/**
 * Interfaz del Servicio de Pedidos.
 */
public interface PedidoService {

	public Pedido getPedidoById(String id);
	
	public Pedido crearPedido();
	
	public Pedido agregarPiezaAlPedido(String pedidoId, String piezaId);

	public Pedido efectivizarPedido(String pedidoId);

	public Pedido cancelarPedido(String pedidoId);

	public List<Pedido> loadPedidosByEstado(String estadoDePedido);

}
