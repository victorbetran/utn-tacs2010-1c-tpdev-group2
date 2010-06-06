package org.utn.tacs.tp.group2.service.definition;

import java.util.List;

import org.utn.tacs.tp.group2.pedido.Pedido;

/**
 * Interfaz del Servicio de Pedidos.
 */
public interface PedidoService {

	public Pedido getPedidoById(Long id);
	
	public Pedido crearPedido();
	
	public void agregarPieza(Long pedidoId, Long piezaId);

	public void efectivizarPedido(Pedido pedido);

	public void cancelarPedido(Pedido pedido);

	public List<Pedido> loadPedidosByEstado(String estadoDePedido);
}
