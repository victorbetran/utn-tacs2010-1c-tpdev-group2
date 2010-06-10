package org.utn.tacs.tp.group2.service.pedido;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.daos.exceptions.PedidoInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoDAOMock extends PedidoDAO{

	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@Override
	public void save(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
	@Override
	public List<Pedido> findAll() {
		return this.pedidos;
	}
	
	@Override
	public Pedido findByID(Long id) {
		for (Pedido pedido : this.pedidos) {
			if(pedido.getId().equals(id)){
				return pedido;
			}
		}
		
		throw new PedidoInexistenteException("");
	}
	
	@Override
	public Boolean isPersisted(Pedido t) {
		return this.pedidos.contains(t);
	}
	
	@Override
	public List<Pedido> findByEstado(EstadoPedido tipoEstado) {
		List<Pedido> toReturn = new ArrayList<Pedido>();

		for (Pedido pedido : this.pedidos) {
			if(pedido.getEstado().equals(tipoEstado)){
				toReturn.add(pedido);
			}
		}

		return toReturn;
	}

	@Override
	protected RuntimeException getNotFoundObjectException(Long id) {
		return null;
	}

}
