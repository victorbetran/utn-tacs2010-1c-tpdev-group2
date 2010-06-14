package org.utn.tacs.tp.group2.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.utn.tacs.tp.group2.daos.exceptions.PedidoInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PedidoService;

/**
 * Implementacion del Servicio de Pedidos.
 */
public class PedidoServiceImpl implements PedidoService {

	@Autowired()
	private PedidoDAO pedidoDAO;

	@Autowired()
	private PiezaDAO piezaDAO;
	
//	public Pedido crearPedido() {
//		Pedido pedidoNuevo = Pedido.create();
//		this.pedidoDAO.save(pedidoNuevo);
//		return pedidoNuevo;
//	}
	
	public void pedidoCreado(Pedido pedido) {
		
	}

	public void agregarPiezaAlPedido(String pedidoId, String piezaId) {
		Pedido pedido = this.pedidoDAO.findByID(Long.valueOf(pedidoId));
		Pieza pieza = this.piezaDAO.findByID(Long.valueOf(piezaId));
		
		pedido.addPieza(pieza);
	}
	
	public void cancelarPedido(String pedidoId) {
		Pedido pedido = this.pedidoDAO.findByID(Long.valueOf(pedidoId));
		pedido.cancelar();
	}

	public void efectivizarPedido(String pedidoId) {
		Pedido pedido = this.pedidoDAO.findByID(Long.valueOf(pedidoId));
		pedido.efectivizar();
	}

	public Pedido loadPedidoById(String id) {
		try{
			return this.pedidoDAO.findByID(Long.valueOf(id));
		}
		catch (RuntimeException e) {
			return null;
		}
	}
	
	public PedidoDTO getPedidoById(String pedidoId) {
		try{
			return new PedidoDTO(this.pedidoDAO.findByID(Long.valueOf(pedidoId)));
		}
		catch (PedidoInexistenteException e) {
			return null;
		}
	}
	
	public List<PedidoDTO> getPedidosByEstado(String estadoDePedido) {
		List<PedidoDTO> toReturn = new ArrayList<PedidoDTO>();
		
		for (Pedido p : this.pedidoDAO.findByEstado(EstadoPedido.estadoByDescripcion(estadoDePedido))) {
			toReturn.add(new PedidoDTO(p));
		}
		
		return toReturn;
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
