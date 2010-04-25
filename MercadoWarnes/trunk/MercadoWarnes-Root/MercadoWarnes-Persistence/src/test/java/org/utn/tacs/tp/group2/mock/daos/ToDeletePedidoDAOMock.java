package org.utn.tacs.tp.group2.mock.daos;

import java.util.List;

import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;


public class ToDeletePedidoDAOMock extends PedidoDAO {

	@Override
	public List<Pedido> findByEstado(EstadoPedido estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido findByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

//	private List<Pedido> pedidos;
//	
//	public ToDeletePedidoDAOMock() {
//		this.pedidos = new ArrayList<Pedido>();
//	}
//	
//	public List<Pedido> findByEstado(EstadoPedido estado) {
//		List<Pedido> result = new ArrayList<Pedido>();
//		for(Pedido pedido : this.pedidos){
//			if(pedido.getEstado().equals(estado))
//				result.add(pedido);
//		}
//		return result;
//	}
//
//	public Pedido findByID(Long id) {
//		Pedido result = null;
//		for(Pedido pedido : this.pedidos){
//			if(pedido.getId().equals(id)){
//				result = pedido;
//				break;
//			}
//		}
//		if(result == null)
//			throw new PedidoInexistenteException("No existe la pieza con id: '" + id + "'.");
//		return result;
//	}
//
//	public void save(Pedido pedido) {
//		this.pedidos.add(pedido);
//	}

}
