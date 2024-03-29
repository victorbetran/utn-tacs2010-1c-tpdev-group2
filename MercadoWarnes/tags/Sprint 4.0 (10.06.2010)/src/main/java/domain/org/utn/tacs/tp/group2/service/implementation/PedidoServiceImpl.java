package org.utn.tacs.tp.group2.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PedidoService;

/**
 * Implementacion del Servicio de Pedidos.
 */
@Transactional
public class PedidoServiceImpl implements PedidoService {

	@Autowired(required=true)
	private PedidoDAO pedidoDAO;

	@Autowired(required=true)
	private PiezaDAO piezaDAO;
	
	public Pedido crearPedido() {
		Pedido pedidoNuevo = Pedido.create();
		this.pedidoDAO.save(pedidoNuevo);
		return pedidoNuevo;
	}

	public Pedido agregarPiezaAlPedido(String pedidoId, String piezaId) {
		Pedido pedido = this.pedidoDAO.findByID(Long.valueOf(pedidoId));
		Pieza pieza = this.piezaDAO.findByID(Long.valueOf(piezaId));
		
		pedido.addPieza(pieza);
		
		return pedido;
	}
	
	public Pedido cancelarPedido(String pedidoId) {
		try{
			Pedido pedido = this.pedidoDAO.findByID(Long.valueOf(pedidoId));
			pedido.cancelar();
			return pedido;
		}catch (RuntimeException e) {
			return null;
		}
	}

	public Pedido efectivizarPedido(String pedidoId) {
		try{
			Pedido pedido = this.pedidoDAO.findByID(Long.valueOf(pedidoId));
			pedido.efectivizar();
			return pedido;
		}catch (RuntimeException e) {
			return null;
		}
	}

	public Pedido loadPedidoById(String id) {
		try{
			return this.pedidoDAO.findByID(Long.valueOf(id));
		}
		catch (RuntimeException e) {
			return null;
		}
	}
	
	public Pedido getPedidoById(String pedidoId) {
		return this.pedidoDAO.getByID(Long.valueOf(pedidoId));
	}
	
	public List<Pedido> getPedidosByEstado(String estadoDePedido) {
		return this.pedidoDAO.findByEstado(EstadoPedido.estadoByDescripcion(estadoDePedido));
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
