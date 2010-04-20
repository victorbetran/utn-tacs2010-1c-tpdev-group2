package org.utn.tacs.tp.group2.daos.mocks;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoInexistenteException;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

import com.eaio.uuid.UUID;

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

	public Pedido findByID(UUID id) {
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
