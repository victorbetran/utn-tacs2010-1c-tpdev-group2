package org.utn.tacs.tp.group2.mocks;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.daos.PedidoDAO;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoInexistenteException;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class PedidoDAOMock implements PedidoDAO {

	private List<Pedido> pedidos;
	
	public PedidoDAOMock() {
		this.pedidos = new ArrayList<Pedido>();
	}
	
	public List<Pedido> findByEstado(EstadoPedido estado) {
		List<Pedido> result = new ArrayList<Pedido>();
		for(Pedido pedido : this.pedidos){
			if(pedido.getEstado().equals(estado))
				result.add(pedido);
		}
		return result;
	}

	public Pedido findByID(String id) {
		Pedido result = null;
		for(Pedido pedido : this.pedidos){
			if(pedido.getId().equals(id)){
				result = pedido;
				break;
			}
		}
		if(result == null)
			throw new PedidoInexistenteException("No existe la pieza con id: '" + id + "'.");
		return result;
	}

	public void save(Pedido pedido) {
		this.pedidos.add(pedido);
	}

}
